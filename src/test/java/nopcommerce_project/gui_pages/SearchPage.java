package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
    // Elements
    private By productLnk = By.xpath("//h2/a[contains(.,'Apple MacBook Pro 13-inch')]");


    //Actions
    public void navigateToProductInfoPage(){
        ElementActions.click(driver, productLnk);
    }

    //Validations
    public void verifyTheProductLink(){
        Assertions.assertTrue
                (ElementActions.isElementDisplayed(driver,productLnk));
        Assertions.assertElementAttribute
                        (driver,
                        productLnk,
                        Assertions.ElementAttributeType.TEXT,
                        "Apple MacBook Pro 13-inch" );
    }
}
