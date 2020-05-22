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
import objectModels.gui.RegisterPage;

public class TestRegistration extends BaseClass {

    private WebDriver browser;

    private RegisterPage registerPage;
    private GoCardiHomePage home;

    @Test
    public void checkRegistrationWithValidCredentials() {
	registerPage.typeFirstName(getTestData("firstName"));
	registerPage.typeLastName(getTestData("lastName"));
	registerPage.typeEmail(getTestData("email"));
	registerPage.typePassword(getTestData("password"));
	registerPage.clickOnConfirmTermsAndConditionsCheckBox();
	registerPage.clickOnRegisterButton();
	Assertions.assertEquals(getTestData("successfulRegisterMessage"), registerPage.getSuccessRegisterText(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testLocalFirstNameValidationWithMinChar() {
	registerPage.typeFirstName(getTestData("invalidNameLetter"));
	Assertions.assertEquals(getTestData("Text_Filed_Min_Char_Error"),
		registerPage.getLocalFirstNameValidationError(), AssertionComparisonType.CASE_INSENSITIVE,
		AssertionType.POSITIVE);
    }

    @Test
    public void testLocalFirstNameValidationWithNumbers() {
	registerPage.typeLastName(getTestData("invalidNameNumber"));
	Assertions.assertEquals(getTestData("Text_Field_Num_Error"), registerPage.getLocalLastNameValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testLocalPasswordValidation() {
	registerPage.typePassword(getTestData("invalidNameNumber"));
	Assertions.assertEquals(getTestData("Password_Error"), registerPage.getLocalPasswordValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testLocalEmailValidation() {
	registerPage.typeEmail(getTestData("inavlidEmailForLocalValidation"));
	Assertions.assertEquals(getTestData("Email_Local_Error"), registerPage.getLocalEmailValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testBackendEmailValidationForTakenEmail() {
	registerPage.typeFirstName(getTestData("firstName"));
	registerPage.typeLastName(getTestData("lastName"));
	registerPage.typeEmail(getTestData("email"));
	registerPage.typePassword(getTestData("password"));
	registerPage.clickOnConfirmTermsAndConditionsCheckBox();
	registerPage.clickOnRegisterButton();
	Assertions.assertEquals(getTestData("Taken_Email_Error"), registerPage.getEmailBackendValidationError(),
		AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
    }

    @Test
    public void testBackendEmailValidationForInvalidEmail() {
	registerPage.typeFirstName(getTestData("firstName"));
	registerPage.typeLastName(getTestData("lastName"));
	registerPage.typeEmail(getTestData("inavlidEmailForBackendValidation"));
	registerPage.typePassword(getTestData("password"));
	registerPage.clickOnConfirmTermsAndConditionsCheckBox();
	registerPage.clickOnRegisterButton();
	Assertions.assertEquals(getTestData("Invalid_Email_Backend_Error"),
		registerPage.getEmailBackendValidationError(), AssertionComparisonType.CONTAINS,
		AssertionType.POSITIVE);
    }

    @Test
    public void testRegisterButtonClickabilityWithoutTermsAndConditionsConfirmed() {
	registerPage.typeFirstName(getTestData("firstName"));
	registerPage.typeLastName(getTestData("lastName"));
	registerPage.typeEmail(getTestData("inavlidEmailForBackendValidation"));
	registerPage.typePassword(getTestData("password"));
	Assertions.assertTrue(registerPage.checkRegisterButtonStatus(), AssertionType.NEGATIVE);
    }

    @BeforeMethod
    public void beforeMethod() {
	browser = BrowserFactory.getBrowser();
	home = new GoCardiHomePage(browser);
	registerPage = new RegisterPage(browser);

	BrowserActions.navigateToURL(browser, getTestData("URL"));
	home.changeLangToEnglish();
	home.clickOnRegister();
    }

    @AfterMethod
    public void afterMethod() {
	BrowserActions.closeCurrentWindow(browser);
    }
}
