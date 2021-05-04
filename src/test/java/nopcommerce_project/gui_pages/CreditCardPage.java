package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreditCardPage {

    private WebDriver driver;
    public CreditCardPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elements
    private By cardHolderName = By.id("CardholderName");
    private By CardNumber = By.id("CardNumber");
    private By ExpiryMonth = By.id("ExpireMonth");
    private By expiryYear = By.id("ExpireYear");
    private By cardCode = By.id("CardCode");
    //Actions
    public void fillCreditData(String holderName,String cardNumber,
                               String expiryMon,String expireYear,String Code){
        new ElementActions(driver)
                .type(cardHolderName,holderName)
                .type(CardNumber,cardNumber)
                .select(ExpiryMonth,expiryMon)
                .select(expiryYear,expireYear)
                .type(cardCode,Code);
    }
}
