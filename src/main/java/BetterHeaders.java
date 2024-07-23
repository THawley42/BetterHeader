import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class BetterHeaders implements BurpExtension {

    private static final String SETTINGS_KEY = "BetterHeader.settings.";


    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("BetterHeaders");
        api.logging().logToOutput("BetterHeaders v0.4");
        api.logging().logToOutput("Created by: lor, Updated and modified by: Theron Hawley");



        //deletes settings just in case
        /*api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"init");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderName");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderPrefix");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsHardcoded");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsRegex");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HardcodedText");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"RegexText");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsAdding");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsReplacing");*/

        //setting up settings if loading the extention for the first time
        if(api.persistence().preferences().getBoolean(SETTINGS_KEY+"init") == null){
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"init", true);
            api.persistence().preferences().setString(SETTINGS_KEY+"HeaderName", "Authorization");
            api.persistence().preferences().setString(SETTINGS_KEY+"HeaderPrefix", "Bearer ");
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsHardcoded", false);
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsRegex", false);
            api.persistence().preferences().setString(SETTINGS_KEY+"HardcodedText", "<insert static JWT token here>");
            api.persistence().preferences().setString(SETTINGS_KEY+"RegexText", "access_token\":\"(.*?)\"");
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsAdding", true);
            api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsReplacing", true);
        }

        // Initiate the burp Tab
        BurpTab tab = new BurpTab(api);
        tab.updateFinalResultLabel(api);
        api.userInterface().registerSuiteTab("BetterHeader", tab);
        //Setup the session handler
        api.http().registerSessionHandlingAction(new SessionHandler(api));

    }
}
