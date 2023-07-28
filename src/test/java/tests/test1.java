package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;

public class test1 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"lpsami1", "123456", new File("src/test/resources/downloads/sami.jpg"), "Self-portrait"}};
    }
    @Test(dataProvider = "getData")
    public void createNewPost(String username, String password, File file, String capText) {
        System.out.println("1.Go to homepage");
            HomePage homePage = new HomePage(driver);
            homePage.openSiteURl();

        System.out.println("2.Login with existing user");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3.Go to profile page and get existing counts of posts");
        header.goToProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();
        int existingPosts = profilePage.getExistingPostCount();

        System.out.println("4.Go to new post page");
        header.goToNewPost();
        NewPostPage postPage = new NewPostPage(driver);
        postPage.verifyUrl();

        System.out.println("5.Upload a new picture");
        postPage.uploadImage(file);

        System.out.println("6.Verify that this picture is visible");
        postPage.waitForImageToBeVisible();

        System.out.println("7.Verify the name of the picture is correct");
        Assert.assertEquals(postPage.getImageFileName(), file.getName());

        System.out.println("8.Populate the post caption");
        postPage.populateCaption(capText);

        System.out.println("9.Click create post");
        postPage.clickSubmitBtn();
        profilePage.verifyUrl();

        System.out.println("10.Verify the post number is correct");
        int currentPostCount = profilePage.getExistingPostCount();
        Assert.assertEquals(currentPostCount, existingPosts + 1, "Post number doesn't match");

        System.out.println("11.Open the last post");
        profilePage.openPostByIndex(currentPostCount - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

    }
}
