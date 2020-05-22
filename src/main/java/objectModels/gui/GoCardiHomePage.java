package objectModels.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class GoCardiHomePage {

    private WebDriver browser;

    private By englishButton = By.linkText("English");
    private By register = By.linkText("Register");
    private By login = By.linkText("LogIn");

    // After LogIn
    private By My_Account = By.id("navbarDropdown");

    public GoCardiHomePage(WebDriver browser) {
	this.browser = browser;
    }

    public void changeLangToEnglish() {
	ElementActions.click(browser, englishButton);
    }

    public RegisterPage clickOnRegister() {
	ElementActions.click(browser, register);
	return new RegisterPage(browser);
    }

    public LoginPage clickOnLogIn() {
	ElementActions.click(browser, login);
	return new LoginPage(browser);
    }

    public boolean isMyAccountDisplayed() {
	return ElementActions.isElementDisplayed(browser, My_Account);
    }
}
