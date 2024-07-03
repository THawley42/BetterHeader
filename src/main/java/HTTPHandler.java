import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HTTPHandler implements HttpHandler {

    private static final String SETTINGS_KEY = "BetterHeader.settings.";
    private final MontoyaApi api;

    public HTTPHandler (MontoyaApi api){
        this.api = api;
    }
    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
        String token = null;
        if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded")) {
            // token has priority over regexp
            token = api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText");
        /*} else if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex")) {
            if (macroItems.length == 0) {
                api.logging().raiseErrorEvent("No macro configured or macro did not return any response");
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

            // go through all macros and run the regular expression on their body
            for (int i = 0; i < macroItems.length; i++) {
                byte[] _responseBody = macroItems[i].getResponse();
                if (_responseBody == null) return null;
                IResponseInfo macroResponse = helpers.analyzeResponse(_responseBody);
                if (macroResponse == null ) return null;
                String responseBody = helpers.bytesToString(_responseBody);
                Matcher m = p.matcher(responseBody);
                if (m.find()) {
                    token = m.group(1);
                    if (token != null && token.length() > 0) {
                        // found it
                        break;
                    }
                }
            }*/
        } else { // using the 'disable' button
            return null;
        }

        String headerName = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName");
        String headerValuePrefix = api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix");

        String headerVal = headerValuePrefix + token;
        //future code: (note rearrange code to fit one if nest?)
        if (token == null) {
            // nothing found: failing silently to avoid polluting the logs
            api.logging().raiseErrorEvent("No token found");
            return null;
        }else if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing")){
            HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            api.logging().logToOutput("Added header: '" + headerName + ": " + headerVal + "'");
            return RequestToBeSentAction.continueWith(request);
        }else if(!api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing") && httpRequestToBeSent.hasHeader(headerName)){
            HttpRequest request = httpRequestToBeSent.withUpdatedHeader(headerName, headerValuePrefix + token);
            api.logging().logToOutput("Added header: '" + headerName + ": " + headerVal + "'");
            return RequestToBeSentAction.continueWith(request);
        }else if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding") && !api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing") && !httpRequestToBeSent.hasHeader(headerName)){
            HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
            api.logging().logToOutput("Added header: '" + headerName + ": " + headerVal + "'");
            return RequestToBeSentAction.continueWith(request);
        }else{
            return null;
        }

        //if add and replace

        //else if replace and not add and header present
        //HttpRequest request = httpRequestToBeSent.withUpdatedHeader(headerName, headerValuePrefix + token);
        //else if add and header not present
        //HttpRequest request = httpRequestToBeSent.withHeader(headerName, headerValuePrefix + token);
        //else
        //return null


    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return null;
    }
}
