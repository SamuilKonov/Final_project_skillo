package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage extends BasePage {
    private final String BASE_URL = "http://training.skillo-bg.com:4200/users/";

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;
    @FindBy(css = ".btn-all")
    WebElement goToAllPosts;
    @FindBy(css = ".fas.fa-user-edit.ng-star-inserted")
    WebElement editButton;
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifyUrl() {
        verifyUrlContains(BASE_URL);
    }
    public int getExistingPostCount() {
        return existingPosts.size();
    }
    public void openPostByIndex(int index) {
        if (existingPosts.size() > 0 && index >= 0 && index < existingPosts.size()) {
            clickElement(existingPosts.get(index));
        } else {
            System.out.println("No posts available.");
        }
    }
    public void goToAllPosts(){
        clickElement(goToAllPosts);
    }
    public void editBtn() {
        clickElement(editButton);
    }
}