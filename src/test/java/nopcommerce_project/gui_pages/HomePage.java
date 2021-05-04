package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    //constructor

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Element Locators
    private By registerLnk = By.xpath("//a[contains(.,'Register')]");
    private By search_TextBox = By.id("small-searchterms");
    private By searchButton = By.xpath("//button[contains(.,'Search')]");
    private By loginLnk = By.linkText("Log in");
    private By logoutLnk = By.linkText("Log out");
    // Actions on elements
    public void navigateToRegisterPage(){
        ElementActions.click(driver, registerLnk);
    }
    public void userLogsOut(){
        ElementActions.click(driver, logoutLnk);
    }
    public void navigateToLoginPage(){
        ElementActions.click(driver, loginLnk);
    }
    public void searchForProduct(String product){
        new ElementActions(driver)
                .type(search_TextBox, product)
                .click(searchButton);
    }
}
