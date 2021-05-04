package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentMethodPage {

    private WebDriver driver;
    public PaymentMethodPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By checkPayment = By.xpath("(//div[@class='payment-details']/input)[1]");
    private By creditCardPayment = By.xpath("(//div[@class='payment-details']/input)[2]");
    private By continueButton = By.xpath("(//button[contains(text(),'Continue')])[4]");
    private By continueToConfirm = By.xpath("(//button[contains(text(),'Continue')])[5]");
    //Actions
    public void choosePaymentMethod(String paymentMethod){
        if(paymentMethod.equalsIgnoreCase("check")){
            ElementActions.click(driver,checkPayment);
        }else if (paymentMethod.equalsIgnoreCase("creditCard")){
            ElementActions.click(driver,creditCardPayment);
        }
    }
    public void navigateToPaymentInfo(){
        ElementActions.click(driver,continueButton);
    }
    public void navigateToConfirmOrder(){
        ElementActions.click(driver,continueToConfirm);
    }

}
