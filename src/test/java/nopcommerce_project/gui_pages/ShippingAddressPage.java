package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingAddressPage {

    private WebDriver driver;
    public ShippingAddressPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By fn = By.id("ShippingNewAddress_FirstName");
    private By ln = By.id("ShippingNewAddress_LastName");
    private By Email = By.id("ShippingNewAddress.Email");
    private By company = By.name("ShippingNewAddress.Company");
    private By countryList = By.id("ShippingNewAddress_CountryId");
    private By City = By.id("ShippingNewAddress.City");
    private By address1 = By.id("ShippingNewAddress_Address1");
    private By address2 = By.name("//button[contains(text(),'Continue')]");
    private By zipCode = By.id("ShippingNewAddress_ZipPostalCode");
    private By phone = By.id("ShippingNewAddress_PhoneNumber");
    private By fax = By.id("ShippingNewAddress.FaxNumber");
    private By continueButton = By.xpath("(//button[contains(text(),'Continue')])[2]");
    //Actions
    public void navigateToShippingMethod(){
        ElementActions.click(driver,continueButton);
    }
    public void fillShippingAddressData(String fName,String lName,String email,String Company,
                                String country,String city,String Address1,String Address2,
                                String zip,String phoneNum,String faxNum){
        new ElementActions(driver)
                .type(fn,fName)
                .type(ln,lName)
                .type(Email,email)
                .type(company,Company)
                .select(countryList,country)
                .type(City, city)
                .type(address1,Address1)
                .type(address2,Address2)
                .type(zipCode,zip)
                .type(phone,phoneNum)
                .type(fax,faxNum)
                .click(continueButton);
    }
}
