package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class test2 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"lpsami1", "123456"}};
    }

    @Test(dataProvider = "getData")
    public void changePublicInfo(String username, String password) {
        System.out.println("1.Go to homepage");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteURl();

        System.out.println("2.Login with existing user");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3.Go to profile page");
        header.goToProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();

        System.out.println("4.Click on edit profile button");
        profilePage.editBtn();

        System.out.println("5.Clear and fill the public info field");
        PostModal postModal = new PostModal(driver);
        postModal.fillPublicInfoField("Samito");
        postModal.saveButton();
    }
}