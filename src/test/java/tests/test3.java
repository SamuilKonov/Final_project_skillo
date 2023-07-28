package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;


public class test3 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"lpsami1", "123456", new File("src/test/resources/downloads/sami.jpg")}};
    }

    @Test(dataProvider = "getData")
    public void changeProfilePhoto(String username, String password, File file) {

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


        System.out.println("4.Click and change profile photo");
        NewPostPage profilePhoto = new NewPostPage(driver);
        profilePhoto.uploadProfilePicture(file);

        System.out.println("5.Check in green pop-up");
        Assert.assertTrue(driver.findElement(By.id("toast-container")).isDisplayed(), "Pop up message doesn't appear!");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}