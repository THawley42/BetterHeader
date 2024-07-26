import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.sessions.SessionHandlingAction;

public class BetterHeaders implements BurpExtension {

    private static final String SETTINGS_KEY = "BetterHeader.settings.";


    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("BetterHeaders");
        api.logging().logToOutput("BetterHeaders v0.5");
        api.logging().logToOutput("Created by: lor, Updated and modified by: Theron Hawley");



        //deletes settings just in case
        /*api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"init1");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderName1");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderPrefix1");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsHardcoded1");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsRegex1");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HardcodedText1");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"RegexText1");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsAdding1");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsReplacing1");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsOnOffRegex1");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"OnOffRegexText1");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"init2");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderName2");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderPrefix2");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsHardcoded2");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsRegex2");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HardcodedText2");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"RegexText2");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsAdding2");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsReplacing2");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsOnOffRegex2");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"OnOffRegexText2");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"init3");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderName3");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderPrefix3");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsHardcoded3");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsRegex3");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HardcodedText3");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"RegexText3");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsAdding3");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsReplacing3");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsOnOffRegex3");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"OnOffRegexText3");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"init4");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderName4");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HeaderPrefix4");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsHardcoded4");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsRegex4");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"HardcodedText4");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"RegexText4");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsAdding4");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsReplacing4");
        api.persistence().preferences().deleteBoolean(SETTINGS_KEY+"IsOnOffRegex4");
        api.persistence().preferences().deleteString(SETTINGS_KEY+"OnOffRegexText4");
         */

        // Initiate the burp Tab
        BurpTab tab = new BurpTab(api);
        api.userInterface().registerSuiteTab("BetterHeader", tab);
        //Setup the session handlers
        SessionHandlingAction session1 = new SessionHandler(api, "1");
        SessionHandlingAction session2 = new SessionHandler(api, "2");
        SessionHandlingAction session3 = new SessionHandler(api, "3");
        SessionHandlingAction session4 = new SessionHandler(api, "4");
        api.http().registerSessionHandlingAction(session1);
        api.http().registerSessionHandlingAction(session2);
        api.http().registerSessionHandlingAction(session3);
        api.http().registerSessionHandlingAction(session4);

    }
}
