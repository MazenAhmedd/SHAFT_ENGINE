package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {

    private WebDriver driver;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By shoppingCartLabel = By.xpath("//h1[contains(text(),'Shopping cart')]");
    private By productQuantity = By.xpath("//td[@class='quantity']/input");
    private By agreeOnTerms = By.name("termsofservice");
    private By checkoutButton = By.id("checkout");
    //Actions
    public void agreeOnTerms(){
        ElementActions.click(driver, agreeOnTerms);
    }
    public void navigateToCheckout(){
        ElementActions.click(driver, checkoutButton);
    }
    //Validations
    public void verifyShoppingCartPage(){
        Assertions.assertElementAttribute(driver
        ,shoppingCartLabel
        , Assertions.ElementAttributeType.TEXT
        ,"Shopping cart");
    }
    public void verifyProductQuantity(String Qty){
        Assertions.assertElementAttribute(driver
        , productQuantity
        ,"value"
        ,Qty);
    }
}
