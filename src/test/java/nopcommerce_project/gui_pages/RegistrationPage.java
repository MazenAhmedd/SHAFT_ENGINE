package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationPage {

    //Constructor
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    By male_gender = By.id("gender-male");
    By female_gender = By.id("gender-female");
    By fnTxtBox = By.id("FirstName");
    By lnTxtBox = By.id("LastName");
    By dayList = By.name("DateOfBirthDay");
    By monthList = By.name("DateOfBirthMonth");
    By yearList = By.name("DateOfBirthYear");
    By emailTxtBox = By.id("Email");
    By companyTxtBox = By.id("Company");
    By passwordTxtBox = By.id("Password");
    By confirmPasswordTxtBox = By.id("ConfirmPassword");
    By registerButton = By.id("register-button");
    By registeredLabel = By.xpath("//div[contains(text(),'Your registration completed')]");

    //Actions on Elements
    public void fillRegistrationData(String gender, String fn, String ln, String day, String month, String year,
                                     String email, String company, String password) {
        chooseGender(gender);
        new ElementActions(driver)
                .type(fnTxtBox, fn)
                .type(lnTxtBox, ln)
                .select(dayList, day)
                .select(monthList, month)
                .select(yearList, year)
                .type(emailTxtBox, email)
                .type(companyTxtBox, company)
                .type(passwordTxtBox, password)
                .type(confirmPasswordTxtBox, password)
                .click(registerButton);
    }

    private void chooseGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            ElementActions.click(driver, male_gender);
        } else if (gender.equalsIgnoreCase("female")) {
            ElementActions.click(driver, female_gender);
        }
    }

    // Validations
    public By getLabelLocator(){
        return registeredLabel;
    }
    public void verifyTheRegisteredLabel (){
        Assertions.assertEquals("Your registration completed",
                ElementActions.getText(driver, registeredLabel));
        Assertions.assertElementAttribute(driver
        ,registeredLabel, Assertions.ElementAttributeType.TEXT
        ,"Your registration completed");
    }
}

