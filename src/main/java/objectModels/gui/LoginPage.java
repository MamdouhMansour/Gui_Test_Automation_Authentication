package objectModels.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class LoginPage {

    private WebDriver browser;

    private By emailAddress = By.id("email");
    private By Email_Next_Button = By.xpath("//form//button[contains(text(), Next)]");

    private By backendError = By.xpath("//form/div[contains(@class,'alert-danger')]");

    public LoginPage(WebDriver browser) {
	this.browser = browser;
    }

    public void typeEmail(String email) {
	ElementActions.type(browser, emailAddress, email);
    }

    public String getBackendEmailValidationError() {
	return ElementActions.getText(browser, backendError);
    }

    public SetPasswordPage clickOnNextButton() {
	ElementActions.click(browser, Email_Next_Button);
	return new SetPasswordPage(browser);
    }

}
