import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class BetterHeaders implements BurpExtension {

    private static final String SETTINGS_KEY = "BetterHeader.settings.";


    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("BetterHeaders");
        api.logging().logToOutput("BetterHeaders v0.1");
        api.logging().logToOutput("Created by: Theron Hawley");

        BurpTab tab = new BurpTab(api);

        if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"init")== null){
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"init", true);
            api.persistence().preferences().setString(SETTINGS_KEY+"HeaderName", "Authorization");//
            api.persistence().preferences().setString(SETTINGS_KEY+"HeaderPrefix", "Bearer ");//
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsHardcoded", false);//
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsRegex", false);//
            api.persistence().preferences().setString(SETTINGS_KEY+"HardcodedText", "<insert static JWT token here>");//
            api.persistence().preferences().setString(SETTINGS_KEY+"RegexText", "access_token\":\"(.*?)\"");
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsAdding", true);
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsReplacing", true);
            //other stuff to set default settings
        }

        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsAdding", true);
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsReplacing", true);

        // set some default values
        //tab.setHeaderName(DEFAULT_HEADER_NAME);
        //tab.setHeaderValuePrefix(DEFAULT_HEADER_VALUE_PREFIX);
        //tab.setRegExpText(DEFAULT_REGEXP);
        //tab.setHardCodedText(DEFAULT_HARDCODED_VALUE);
        // force update the example label
        tab.updateFinalResultLabel(api);

        api.userInterface().registerSuiteTab("BetterHeader", tab);
        HTTPHandler httphandler = new HTTPHandler(api);
        //api.http().registerHttpHandler(httphandler);
        api.http().registerSessionHandlingAction(new SessionHandler(api));

    }
}
