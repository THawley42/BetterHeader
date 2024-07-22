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

    public SessionHandler (MontoyaApi api){
        this.api = api;
    }

    @Override
    public String name() {
        return "Use for Better Header";
    }

    @Override
    public ActionResult performAction(SessionHandlingActionData sessionHandlingActionData) {
    	
    	//init some values
        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName");
        HttpRequest httpRequestToBeSent = sessionHandlingActionData.request();
        
        //if the adding and replacing setting is on, send to setToken function and return 
        //elif the replacing is on and adding isn't AND it does have the header, send to setToken function and return 
        //elif the adding is on and replacing isn't and the header is not there, send to setToken function and return 
        //else (ie neither setting is on, or adding is on and the header is already there or replacing is on without the header there) just return null
        if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing")){
            return SetToken(sessionHandlingActionData);
        }else if(!api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing") && httpRequestToBeSent.hasHeader(headerName)){
            
            return SetToken(sessionHandlingActionData);
        }else if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && !api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing") && !httpRequestToBeSent.hasHeader(headerName)){
            
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
        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName");
        String headerValuePrefix = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix");
        
        //if the setting is set to hardcoded then grab the value, add the header and return
        //elif its set to regex, try to pull the macro, try to grab the regex, then add the header and return
        if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded")) {
            token = api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText");
            HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            return ActionResult.actionResult(request);
        } else if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex")) {
            List<HttpRequestResponse> macroItems = sessionHandlingActionData.macroRequestResponses();
            //if macro empty, error and return null
            if (macroItems.isEmpty()) {
                api.logging().logToError("No macro configured or macro did not return any response");
                return null;
            }
            String regexp = api.persistence().preferences().getString(SETTINGS_KEY+"RegexText");
            Pattern p;
            //trying to compile the pattern, if it fails log error and return null
            try {
                p = Pattern.compile(regexp);
            } catch (PatternSyntaxException e) {
                api.logging().logToError(e);
                return null;
            }
            
            // go through all macro items and run the regular expression on their body
            for (HttpRequestResponse macroItem : macroItems) {
                String response = macroItem.response().toString();
                if (response.isEmpty()) {
                    api.logging().logToOutput("response null");
                    return null;
                }
                Matcher m = p.matcher(response);
                if (m.find()) {
                    token = m.group();
                    //if the pattern was matched, add the header and return
                    if (token != null && !token.isEmpty()) {
                        HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
                        return ActionResult.actionResult(request);
                    }
                }
            }

        }
        
        return null;
    }
}
