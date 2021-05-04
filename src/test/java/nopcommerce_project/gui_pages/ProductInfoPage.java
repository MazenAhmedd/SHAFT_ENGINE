package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {

    private WebDriver driver ;
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By productNameLabel = By.xpath("//h1[contains(text(),'Apple MacBook Pro 13-inch')]");
    private By productQuantity = By.name("addtocart_4.EnteredQuantity");
    private By addToCartButton = By.id("add-to-cart-button-4");
    private By shoppingCartLink = By.xpath("//a[@class='ico-cart']");
    //Actions
    public void setProductQuantity(String quantity){
        ElementActions.type(driver, productQuantity, quantity);
    }
    public void addToCart(){
        ElementActions.click(driver,addToCartButton);
    }
    public void navigateToShoppingCart(){
        ElementActions.click(driver, shoppingCartLink);
    }
    //Validations
    public void verifyTheProductNameLabel(){
        Assertions.assertTrue(ElementActions.isElementDisplayed(driver,
                productNameLabel));
        Assertions.assertEquals("Apple MacBook Pro 13-inch",
                ElementActions.getText(driver, productNameLabel));
        Assertions.assertElementAttribute(driver
        ,productNameLabel
        , Assertions.ElementAttributeType.TEXT
        ,"Apple MacBook Pro 13-inch");
    }
}
