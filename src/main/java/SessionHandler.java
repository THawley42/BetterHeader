import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.sessions.ActionResult;
import burp.api.montoya.http.sessions.SessionHandlingAction;
import burp.api.montoya.http.sessions.SessionHandlingActionData;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SessionHandler implements SessionHandlingAction {

    private static final String SETTINGS_KEY = "BetterHeader.settings.";
    private final MontoyaApi api;
    private final String tab;

    public SessionHandler (MontoyaApi api, String tab){
        this.api = api;
        this.tab = tab;
    }

    @Override
    public String name() {
        return "Use for Better Header (Tab "+tab+")";
    }

    @Override
    public ActionResult performAction(SessionHandlingActionData sessionHandlingActionData) {
    	
    	//init some values
        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName"+tab);
        HttpRequest httpRequestToBeSent = sessionHandlingActionData.request();
        
        //if the adding and replacing setting is on, send to setToken function and return 
        //elif the replacing is on and adding isn't AND it does have the header, send to setToken function and return 
        //elif the adding is on and replacing isn't and the header is not there, send to setToken function and return 
        //else (ie neither setting is on, or adding is on and the header is already there or replacing is on without the header there) just return null
        if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding"+tab) && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing"+tab)){
            return SetToken(sessionHandlingActionData);
        }else if(!api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding"+tab) && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing"+tab) && httpRequestToBeSent.hasHeader(headerName)){
            
            return SetToken(sessionHandlingActionData);
        }else if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding"+tab) && !api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing"+tab) && !httpRequestToBeSent.hasHeader(headerName)){
            
            return SetToken(sessionHandlingActionData);
        }else{
            return null;
        }
    }

    //logic for adding/replacing the header
    private ActionResult SetToken(SessionHandlingActionData sessionHandlingActionData){
    	
    	//init some values
        String token;
        HttpRequest httpRequestToBeSent = sessionHandlingActionData.request();
        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName"+tab);
        String headerValuePrefix = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix"+tab);

        //first checks if the on/off regex is turned on and immediately returns null if the regex wasn't found
        if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsOnOffRegex"+tab) && !api.persistence().preferences().getString(SETTINGS_KEY+"OnOffRegexText"+tab).isEmpty()){
            Pattern p2;
            //trying to compile the pattern, if it fails log error and return null
            try {
                p2 = Pattern.compile(api.persistence().preferences().getString(SETTINGS_KEY+"OnOffRegexText"+tab));
            } catch (PatternSyntaxException e) {
                api.logging().logToError(e);
                return null;
            }
            if(RegexFinder(p2, httpRequestToBeSent.toString()) == null){
                return null;
            }
        }
        //then if the setting is set to hardcoded then grab the value, add the header and return
        //elif its set to regex, try to pull the macro, try to grab the regex, then add the header and return
        if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded"+tab)) {
            token = api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText"+tab);
            HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            return ActionResult.actionResult(request);
        } else if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex"+tab)) {
            List<HttpRequestResponse> macroItems = sessionHandlingActionData.macroRequestResponses();
            //if macro empty, error and return null
            if (macroItems.isEmpty()) {
                api.logging().logToError("No macro configured or macro did not return any response");
                return null;
            }
            String regexp = api.persistence().preferences().getString(SETTINGS_KEY+"RegexText"+tab);
            Pattern p;
            //trying to compile the pattern, if it fails log error and return null
            try {
                p = Pattern.compile(regexp);
            } catch (PatternSyntaxException e) {
                api.logging().logToError(e);
                return null;
            }
            
            // go through all macro items and run the regular expression on their responses
            for (HttpRequestResponse macroItem : macroItems) {
                String response = macroItem.response().toString();

                if (response.isEmpty()) {
                    api.logging().logToOutput("Macro response is null");
                    return null;
                }
                token = RegexFinder(p, response);
                if (token != null){
                    HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
                    return ActionResult.actionResult(request);
                }
            }

        }
        
        return null;
    }

    //matches the pattern to the input string
    private String RegexFinder (Pattern p, String body){
        String result;
        Matcher m = p.matcher(body);
        if (m.find()) {
            result = m.group();
            if (result != null && !result.isEmpty()) {
                return result;
            }
        }
        return null;
    }
}
