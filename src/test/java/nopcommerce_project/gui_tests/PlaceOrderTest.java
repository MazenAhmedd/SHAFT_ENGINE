package nopcommerce_project.gui_tests;

import com.github.javafaker.Faker;
import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import nopcommerce_project.gui_pages.HomePage;
import nopcommerce_project.gui_pages.LoginPage;
import nopcommerce_project.gui_pages.RegistrationPage;
import nopcommerce_project.gui_pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceOrderTest {
    WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    @BeforeClass
    void beforeClass() {
        WebDriver driver = DriverFactory.getDriver();
        BrowserActions.navigateToURL(driver, "https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
    }

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String company = faker.company().name();
    String password = faker.internet().password
            (8,
                    9,
                    true,
                    false,
                    true);

    @Test
    @Description("Registration TC with SHAFT_ENGINE")
    @Severity(SeverityLevel.CRITICAL)
    void userSignUp() {
        homePage.navigateToRegisterPage();
        registrationPage.fillRegistrationData
                ("male", firstName, lastName, "18", "12",
                        "1992", email, company, password);
        registrationPage.verifyTheRegisteredLabel();
        homePage.userLogsOut();
    }

    @Test(dependsOnMethods = {"userSignUp"})
    @Severity(SeverityLevel.CRITICAL)
    void userCanPlaceOrder(){
        homePage.navigateToLoginPage();
        loginPage.loginWithValidData(email, password);
        homePage.searchForProduct("Apple MacBook Pro 13-inch");
        searchPage.verifyTheProductLink();
        searchPage.navigateToProductInfoPage();
    }
}
