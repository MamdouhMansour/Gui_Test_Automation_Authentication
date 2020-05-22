package objectModels.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class RegisterPage {
    private WebDriver browser;

    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By emailAddress = By.id("email");
    private By password = By.id("password");
    private By checkBox1 = By.xpath("//label[@for='checkbox1']");

    private By registerButton = By.xpath("//button[contains(@class, 'register')]");

    private By registerCompleted = By.xpath("//h2[contains(text(),'Registration Completed')]");

    private By firstNameError = By.id("firstname-error");
    private By lastNameError = By.id("lastname-error");
    private By passwordError = By.id("password-error");
    private By emailLocalError = By.id("email-error");

    private By backendError = By.xpath("//form/div[contains(@class,'alert-danger')]");

    public RegisterPage(WebDriver browser) {
	this.browser = browser;
    }

    public void typeFirstName(String name) {
	ElementActions.type(browser, firstName, name);
    }

    public void typeLastName(String name) {
	ElementActions.type(browser, lastName, name);
    }

    public void typeEmail(String email) {
	ElementActions.type(browser, emailAddress, email);
    }

    public void typePassword(String pass) {
	ElementActions.type(browser, password, pass);
    }

    public void clickOnConfirmTermsAndConditionsCheckBox() {
	ElementActions.click(browser, checkBox1);
    }

    public void clickOnRegisterButton() {
	ElementActions.click(browser, registerButton);
    }

    public String getLocalFirstNameValidationError() {
	return ElementActions.getText(browser, firstNameError);
    }

    public String getLocalLastNameValidationError() {
	return ElementActions.getText(browser, lastNameError);
    }

    public String getLocalPasswordValidationError() {
	return ElementActions.getText(browser, passwordError);
    }

    public String getLocalEmailValidationError() {
	return ElementActions.getText(browser, emailLocalError);
    }

    public String getEmailBackendValidationError() {
	return ElementActions.getText(browser, backendError);
    }

    public boolean checkRegisterButtonStatus() {
	return browser.findElement(registerButton).isEnabled();
    }

    public String getSuccessRegisterText() {
	return ElementActions.getText(browser, registerCompleted);
    }
}
