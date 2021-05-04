package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingMethodPage {

    private WebDriver driver;
    public ShippingMethodPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By groundMethod =
            By.xpath("(//ul[@class='method-list']/li/div[@class='method-name']/input)[1]");
    private By nextDayAirMethod =
            By.xpath("(//ul[@class='method-list']/li/div[@class='method-name']/input)[2]");
    private By secondDayAir =
            By.xpath("(//ul[@class='method-list']/li/div[@class='method-name']/input)[3]");
    private By continueButton = By.xpath("(//button[contains(text(),'Continue')])[3]");
    //Actions
    public void chooseShippingMethod(String shippingMethod) {
        if (shippingMethod.equalsIgnoreCase("ground")) {
            ElementActions.click(driver, groundMethod);
        } else if (shippingMethod.equalsIgnoreCase("next day air ")) {
            ElementActions.click(driver, nextDayAirMethod);
        } else if (shippingMethod.equalsIgnoreCase("2nd day air ")) {
            ElementActions.click(driver, secondDayAir);
        }
    }
    public void navigateToPaymentMethod(){
        ElementActions.click(driver, continueButton);
    }
}

