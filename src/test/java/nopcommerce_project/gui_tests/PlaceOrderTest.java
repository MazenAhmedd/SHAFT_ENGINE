package nopcommerce_project.gui_tests;

import com.github.javafaker.Faker;
import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import nopcommerce_project.gui_pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceOrderTest {

    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private ProductInfoPage productInfoPage;
    private ShoppingCartPage shoppingCartPage;
    private BillingAddressPage billingAddressPage;
    private ShippingAddressPage shippingAddressPage;
    private ShippingMethodPage shippingMethodPage;
    private PaymentMethodPage paymentMethodPage;
    private ConfirmOrderPage confirmOrderPage;
    private CreditCardPage creditCardPage;


    @BeforeClass
    void beforeClass() {
        WebDriver driver = DriverFactory.getDriver();
        BrowserActions.navigateToURL(driver, "https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        productInfoPage = new ProductInfoPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        billingAddressPage = new BillingAddressPage(driver);
        shippingAddressPage = new ShippingAddressPage(driver);
        shippingMethodPage = new ShippingMethodPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);
        confirmOrderPage = new ConfirmOrderPage(driver);
        creditCardPage = new CreditCardPage(driver);
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
    private String QTY = "10";
    private String productName = "Apple MacBook Pro 13-inch";
    private String country = faker.country().name();
    private String city = faker.country().capital();
    private String address1 = faker.address().fullAddress();
    private String address2 = faker.address().fullAddress();
    private String zip = faker.address().zipCode();
    private String phone = faker.phoneNumber().phoneNumber();
    private String fax = faker.phoneNumber().phoneNumber();
    private String shippingMethod = "next day air ";
    private String PaymentMethod = "creditCard";
    private String holderName = faker.name().fullName();
    private String CardNumber = "371449635398431";
    private String Month = "12";
    private String Year = "2025";
    private String CVV = "123";
    @Test(dependsOnMethods = {"userSignUp"})
    @Severity(SeverityLevel.CRITICAL)
    void userCanPlaceOrder(){
        homePage.navigateToLoginPage();
        loginPage.loginWithValidData(email, password);
        homePage.searchForProduct(productName);
        searchPage.verifyTheProductLink();
        searchPage.navigateToProductInfoPage();
        productInfoPage.verifyTheProductNameLabel();
        productInfoPage.setProductQuantity(QTY);
        productInfoPage.addToCart();
        productInfoPage.navigateToShoppingCart();
        shoppingCartPage.verifyShoppingCartPage();
        shoppingCartPage.verifyProductQuantity(QTY);
        shoppingCartPage.agreeOnTerms();
        shoppingCartPage.navigateToCheckout();
        billingAddressPage.uncheckShipToTheSameAddressIfSelected();
        billingAddressPage.fillBillingAddressData(firstName, lastName,email, company,
                country,city,address1, address2,zip,phone,fax);
        shippingAddressPage.navigateToShippingMethod();
        shippingMethodPage.chooseShippingMethod(shippingMethod);
        shippingMethodPage.navigateToPaymentMethod();
        paymentMethodPage.choosePaymentMethod(PaymentMethod);
        paymentMethodPage.navigateToPaymentInfo();
        creditCardPage.fillCreditData
                (holderName, CardNumber,Month,Year,CVV);
        paymentMethodPage.navigateToConfirmOrder();
        confirmOrderPage.verifyProductQuantity(QTY);
        confirmOrderPage.ConfirmOrder();
        confirmOrderPage.verifySuccessOrder();
        confirmOrderPage.navigateToOrderInfo();
    }
}
