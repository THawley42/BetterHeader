import burp.api.montoya.http.handler.*;

import java.util.regex.PatternSyntaxException;

public class HTTPHandler implements HttpHandler {
    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
        String token = null;

        if (tab.useHardCoded()) {
            // token has priority over regexp
            token = tab.getHardCodedText();
        } else if (tab.useRegExp()) {
            if (macroItems.length == 0) {
                this.callbacks.issueAlert("No macro configured or macro did not return any response");
                return null;
            }String regexp = tab.getRegExpText();
            try {
                p = Pattern.compile(regexp);
            } catch (PatternSyntaxException e) {
                this.callbacks.issueAlert("Syntax error in regular expression (see extension error window)");
                callbacks.printError(e.toString());
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
            }
        } else { // using the 'disable' button
            return null;
        }

        if (token == null) {
            // nothing found: failing silently to avoid polluting the logs
            callbacks.printError("No token found");
            return null;
        }
        String headerName = tab.getHeaderName();
        String headerValuePrefix = tab.getHeaderValuePrefix();

        IRequestInfo rqInfo = helpers.analyzeRequest(currentRequest);
        // retrieve all headers
        ArrayList<String> headers = (ArrayList<String>) rqInfo.getHeaders();
        for (int i = 0; i < headers.size(); i++) {
            if (((String) headers.get(i)).startsWith(headerName + ": " + headerValuePrefix)) {
                // there could be more than one header like this; remove and continue
                headers.remove(i);
            }
        }
        String newHeader = headerName + ": " + headerValuePrefix + token;
        headers.add(newHeader);
        callbacks.printOutput("Added header: '" + newHeader + "'");

        byte[] message = helpers.buildHttpMessage(headers, Arrays.copyOfRange(currentRequest.getRequest(), rqInfo.getBodyOffset(), currentRequest.getRequest().length));
        currentRequest.setRequest(message);
        return null;
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return null;
    }
}
