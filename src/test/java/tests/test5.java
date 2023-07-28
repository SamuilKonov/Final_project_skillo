package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class test5 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"lpsami1", "123456", "lpsami"}};
    }

    @Test(dataProvider = "getData")
    public void deletePost(String username, String password, String user) {
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
        profilePage.goToAllPosts();
        profilePage.verifyUrl();

        System.out.println("4.Select last post");
        profilePage.openPostByIndex(0);

        System.out.println("5.Click delete and yes");
        header.clickDeleteBtn();
        header.clickYesBtn();

        System.out.println("6.Check the pop-up confirmation");
        Assert.assertTrue(driver.findElement(By.id("toast-container")).isDisplayed(), "The picture didn't delete.");
    }
}