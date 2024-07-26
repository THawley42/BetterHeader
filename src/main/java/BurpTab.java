import burp.api.montoya.MontoyaApi;

import javax.swing.*;

public class BurpTab extends JPanel{


    public BurpTab(MontoyaApi api){
        JPanel tOne = new HeaderTab(api, "1");
        JPanel tTwo = new HeaderTab(api, "2");
        JPanel tThree = new HeaderTab(api, "3");
        JPanel tFour = new HeaderTab(api, "4");
        JTabbedPane tabP = new JTabbedPane();
        tabP.addTab("1", null, tOne,null);
        tabP.addTab("2", null, tTwo,null);
        tabP.addTab("3", null, tThree,null);
        tabP.addTab("4", null, tFour,null);
        add(tabP);
    }


}
