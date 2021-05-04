package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmOrderPage {

    private WebDriver driver;
    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By productQuantity = By.xpath("//tr/td/span[@class='product-quantity']");
    private By confirmOrderButton = By.xpath("//button[contains(text(),'Confirm')]");
    private By successOrderLabel =
            By.xpath("//div[@class='title']/strong[contains(.,'successfully processed')]");
    private By orderDetailsLnk = By.xpath("//a[contains(text(),'Click here for order details.')]");
    //Actions
    public void ConfirmOrder(){
        ElementActions.click(driver,confirmOrderButton);
    }
    public void navigateToOrderInfo(){
        ElementActions.click(driver,orderDetailsLnk);
    }
    //Validations
    public void verifyProductQuantity(String Qty){
        Assertions.assertElementAttribute(driver
        ,productQuantity
        , Assertions.ElementAttributeType.TEXT
        ,Qty);
    }
    public void verifySuccessOrder(){
        Assertions.assertElementAttribute(driver
        ,successOrderLabel
        , Assertions.ElementAttributeType.TEXT
        ,"Your order has been successfully processed!");
    }
}
