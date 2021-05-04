package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillingAddressPage {

    private WebDriver driver;
    public BillingAddressPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By shipToSameAddress = By.id("ShipToSameAddress");
    private By shipAddressLabel = By.xpath("//label[contains(text(),'Ship to the same address')]");
    private By fn = By.id("BillingNewAddress_FirstName");
    private By ln = By.id("BillingNewAddress_LastName");
    private By Email = By.name("BillingNewAddress.Email");
    private By company = By.id("BillingNewAddress_Company");
    private By countryList = By.id("BillingNewAddress_CountryId");
    private By city = By.id("BillingNewAddress_City");
    private By address1 = By.id("BillingNewAddress_Address1");
    private By address2 = By.name("BillingNewAddress.Address2");
    private By zipCode = By.name("BillingNewAddress.ZipPostalCode");
    private By phone = By.name("BillingNewAddress.PhoneNumber");
    private By fax = By.id("BillingNewAddress_FaxNumber");
    private By continueButton = By.xpath("(//button[contains(text(),'Continue')])[1]");

    //Actions
    public void uncheckShipToTheSameAddressIfSelected(){
        Assertions.assertElementAttribute(driver
        ,shipAddressLabel
        , Assertions.ElementAttributeType.TEXT
        ,"Ship to the same address");
        if(driver.findElement(shipToSameAddress).isSelected())
        {ElementActions.click(driver, shipToSameAddress);}
    }
    public void fillBillingAddressData(String fName, String lName, String email, String Company,
                                       String country, String City, String Address1, String Address2,
                                       String zip, String phoneNum, String faxNum){
        new ElementActions(driver)
                .type(fn,fName)
                .type(ln,lName)
                .type(Email,email)
                .type(company,Company)
                .select(countryList,country)
                .type(city, City)
                .type(address1,Address1)
                .type(address2,Address2)
                .type(zipCode,zip)
                .type(phone,phoneNum)
                .type(fax,faxNum)
                .click(continueButton);
    }
}
