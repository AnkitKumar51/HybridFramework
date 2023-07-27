package Tests;

import org.testng.annotations.Test;

public class TC06_QpPage extends baseClass{
    @Test(priority = 0,dataProvider = "WebsiteLoginData")
    public void QpPage(String url, String userName, String passWord){
        pageFactory.getLoginPage().Login(url,userName, passWord);
        pageFactory.getQpPage().goToQpSection();
    }

    @Test(priority = 1,dataProvider = "QpPageData")
    public void QpSubPage(String qpName, String qpInstructions){
        pageFactory.getQpPage().addQPData(qpName,qpInstructions);
        pageFactory.getQpPage().submitQP();
        pageFactory.getQpPage().checkQpAddedOrNot(qpName);
//        pageFactory.getQpPage().tableOperation(qpName);
        pageFactory.getQpPage().deleteQP(qpName);
    }
}
