import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class BetterHeaders implements BurpExtension {

    final String DEFAULT_HEADER_NAME = "Authorization";
    final String DEFAULT_HEADER_VALUE_PREFIX = "Bearer ";
    final String DEFAULT_REGEXP = "access_token\":\"(.*?)\"";
    final String DEFAULT_HARDCODED_VALUE = "<insert static JWT token here>";

    private BurpTab tab;

    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("BetterHeaders");
        api.logging().logToOutput("BetterHeaders v0.1");
        api.logging().logToOutput("Created by: Theron Hawley");

        tab = new BurpTab();

        // set some default values
        tab.setHeaderName(DEFAULT_HEADER_NAME);
        tab.setHeaderValuePrefix(DEFAULT_HEADER_VALUE_PREFIX);
        tab.setRegExpText(DEFAULT_REGEXP);
        tab.setHardCodedText(DEFAULT_HARDCODED_VALUE);
        // force update the example label
        tab.updateFinalResultLabel();

        api.userInterface().registerSuiteTab("RepeaterNamer", tab);

    }
}
