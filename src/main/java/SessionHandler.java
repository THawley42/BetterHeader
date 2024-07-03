import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.RequestToBeSentAction;
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
        return "Use regex for Better Header";
    }

    @Override
    public ActionResult performAction(SessionHandlingActionData sessionHandlingActionData) {
        String token = null;
        /*
        if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded")) {
            // token has priority over regexp
            token = api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText");
        } else if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex")) {
            List<HttpRequestResponse> macroItems = sessionHandlingActionData.macroRequestResponses();
            if (macroItems.isEmpty()) {
                api.logging().raiseErrorEvent("No macro configured or macro did not return any response"); //not working like I thought
                api.logging().logToError("No macro configured or macro did not return any response");
                return null;
            }String regexp = api.persistence().preferences().getString(SETTINGS_KEY+"RegexText");
            Pattern p;
            try {
                p = Pattern.compile(regexp);
            } catch (PatternSyntaxException e) {
                api.logging().raiseErrorEvent("Syntax error in regular expression (see extension error window)");
                api.logging().logToError(e);
                return null;
            }
            //api.logging().logToOutput("p compiled and macro up");
            // go through all macros and run the regular expression on their body
            for (HttpRequestResponse macroItem : macroItems) {
                String response = macroItem.response().toString();
                //api.logging().logToOutput("in loop. reponse = \n"+response+"\n");
                if (response.isEmpty()) {
                    api.logging().logToOutput("response null");
                    return null;
                }
                Matcher m = p.matcher(response);
                //api.logging().logToOutput("m compiled");
                if (m.find()) {
                    //api.logging().logToOutput("in m.find: "+m);
                    token = m.group();
                    //if(token == null)api.logging().logToOutput("token is null");
                    //api.logging().logToOutput("token: "+token);
                    if (token != null && !token.isEmpty()) {
                        // found it
                        break;
                    }
                }//else api.logging().logToOutput("m did not find");
            }
            if(token == null || token.isEmpty()) return null;

        } else { // using the 'disable' button
            return null;
        }
*/
        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName");
        String headerValuePrefix = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix");
        HttpRequest httpRequestToBeSent = sessionHandlingActionData.request();
        //future code: (note rearrange code to fit one if nest?)
        /*if (token == null) {
            // nothing found: failing silently to avoid polluting the logs
            api.logging().raiseErrorEvent("No token found");
            return null;
        }else */if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing")){

            //HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            //api.logging().logToOutput("Added header: '" + headerName + ": " + headerValuePrefix + token + "'");
            return SetToken(sessionHandlingActionData);//ActionResult.actionResult(request);
        }else if(!api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing") && httpRequestToBeSent.hasHeader(headerName)){
            //HttpRequest request = httpRequestToBeSent.withUpdatedHeader(headerName, headerValuePrefix + token);
            //api.logging().logToOutput("Added header: '" + headerName + ": " + headerValuePrefix + token + "'");
            return SetToken(sessionHandlingActionData);//ActionResult.actionResult(request);
        }else if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && !api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing") && !httpRequestToBeSent.hasHeader(headerName)){
            //HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            //api.logging().logToOutput("Added header: '" + headerName + ": " + headerValuePrefix + token + "'");
            return SetToken(sessionHandlingActionData);//ActionResult.actionResult(request);
        }else{
            return null;
        }
    }


    private ActionResult SetToken(SessionHandlingActionData sessionHandlingActionData){
        String token = null;
        HttpRequest httpRequestToBeSent = sessionHandlingActionData.request();
        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName");
        String headerValuePrefix = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix");
        if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded")) {

            token = api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText");
            HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            api.logging().logToOutput("Added header: '" + headerName + ": " + headerValuePrefix + token + "'");
            return ActionResult.actionResult(request);
        } else if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex")) {
            List<HttpRequestResponse> macroItems = sessionHandlingActionData.macroRequestResponses();
            if (macroItems.isEmpty()) {
                api.logging().raiseErrorEvent("No macro configured or macro did not return any response"); //not working like I thought
                api.logging().logToError("No macro configured or macro did not return any response");
                return null;
            }String regexp = api.persistence().preferences().getString(SETTINGS_KEY+"RegexText");
            Pattern p;
            try {
                p = Pattern.compile(regexp);
            } catch (PatternSyntaxException e) {
                api.logging().raiseErrorEvent("Syntax error in regular expression (see extension error window)");
                api.logging().logToError(e);
                return null;
            }
            //api.logging().logToOutput("p compiled and macro up");
            // go through all macros and run the regular expression on their body
            for (HttpRequestResponse macroItem : macroItems) {
                String response = macroItem.response().toString();
                //api.logging().logToOutput("in loop. reponse = \n"+response+"\n");
                if (response.isEmpty()) {
                    api.logging().logToOutput("response null");
                    return null;
                }
                Matcher m = p.matcher(response);
                //api.logging().logToOutput("m compiled");
                if (m.find()) {
                    //api.logging().logToOutput("in m.find: "+m);
                    token = m.group();
                    //if(token == null)api.logging().logToOutput("token is null");
                    //api.logging().logToOutput("token: "+token);
                    if (token != null && !token.isEmpty()) {
                        // found it
                        HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
                        api.logging().logToOutput("Added header: '" + headerName + ": " + headerValuePrefix + token + "'");
                        return ActionResult.actionResult(request);
                    }
                }//else api.logging().logToOutput("m did not find");
            }

        }
        return null;
    }
}
