package tests.gui.task;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import com.shaft.validation.Assertions;
import com.shaft.validation.Assertions.AssertionComparisonType;
import com.shaft.validation.Assertions.AssertionType;

import base.BaseClass;
import objectModels.gui.GoCardiHomePage;
import objectModels.gui.LoginPage;
import objectModels.gui.SetPasswordPage;

public class TestLogin extends BaseClass {

    private WebDriver browser;

    private GoCardiHomePage home;
    private LoginPage login;
    private SetPasswordPage setPassowrd;

    @Test
    public void testLoginWithRegisteredCredentials() {
	login.typeEmail(getTestData("email"));
	login.clickOnNextButton();
	setPassowrd.typePassword(getTestData("password"));
	setPassowrd.clickOnLogInButton();
	Assertions.assertTrue(home.isMyAccountDisplayed(), AssertionType.POSITIVE);
    }

    @Test
    public void testValidationOfNotRegisteredEmail() {
	login.typeEmail(getTestData("notRegisteredEmail"));
	login.clickOnNextButton();
	Assertions.assertEquals(getTestData("Not_Registered_Email"), login.getBackendEmailValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testValidationOfInvalidEmail() {
	login.typeEmail(getTestData("inavlidEmailForBackendValidation"));
	login.clickOnNextButton();
	Assertions.assertEquals(getTestData("Invalid_Email_Backend_Error"), login.getBackendEmailValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testPasswordValidation() {
	login.typeEmail(getTestData("RegisteredEmail"));
	login.clickOnNextButton();
	setPassowrd.typePassword(getTestData("invalidPassword"));
	setPassowrd.clickOnLogInButton();
	Assertions.assertEquals(getTestData("Login_Validation_Error"), login.getBackendEmailValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @BeforeMethod
    public void beforeMethod() {
	browser = BrowserFactory.getBrowser();
	home = new GoCardiHomePage(browser);
	login = new LoginPage(browser);
	setPassowrd = new SetPasswordPage(browser);

	BrowserActions.navigateToURL(browser, getTestData("URL"));
	home.changeLangToEnglish();
	home.clickOnLogIn();
    }

    @AfterMethod
    public void afterMethod() {
	BrowserActions.closeCurrentWindow(browser);
    }

}
