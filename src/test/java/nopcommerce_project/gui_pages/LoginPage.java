package nopcommerce_project.gui_pages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Elements
    private By emailTextBox = By.id("Email");
    private By passwordTextBox  = By.id("Password");
    private By rememberMeCheckBox = By.id("RememberMe");
    private By loginButton = By.xpath("//button[contains(text(),'Log in')]");

    // Actions on Elements
    public void loginWithValidData(String email , String password){
       new ElementActions(driver)
               .type(emailTextBox, email)
               .type(passwordTextBox, password)
               .click(rememberMeCheckBox)
               .click(loginButton);
    }
}
