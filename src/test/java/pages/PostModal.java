package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostModal extends BasePage {

    @FindBy(tagName = "app-post-modal")
    WebElement modalDialog;
    @FindBy(css = "input[placeholder='Comment here']")
    WebElement commentField;
    @FindBy(css = "textarea.form-control")
    WebElement publicInfoField;
    @FindBy(css = "button.btn.btn-primary")
    WebElement saveBtn;


    public PostModal(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForDialogAppear() {
        waitForVisibility(modalDialog);
    }

    public void waitForDialogAppear2() {
        waitForVisibility(commentField);
    }

    public void writeComment(String comment) {
        enterText(commentField, comment);
        commentField.sendKeys(Keys.RETURN);
    }

    public String getCommentText() {
        WebElement newComment = driver.findElement(By.cssSelector(".col-12.comment-content"));
        return newComment.getText();
    }
    public void fillPublicInfoField(String Text) {
        publicInfoField.clear();
        enterText(publicInfoField, Text);
    }
    public void saveButton() {
        clickElement(saveBtn);
    }
}