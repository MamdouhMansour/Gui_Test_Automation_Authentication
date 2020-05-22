package objectModels.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class SetPasswordPage {
    private WebDriver browser;

    private By password = By.id("password");
    private By logInButton = By.xpath("//form//div/button");

    public SetPasswordPage(WebDriver browser) {
	this.browser = browser;
    }

    public void typePassword(String pass) {
	ElementActions.type(browser, password, pass);
    }

    public void clickOnLogInButton() {
	ElementActions.click(browser, logInButton);
    }
}
