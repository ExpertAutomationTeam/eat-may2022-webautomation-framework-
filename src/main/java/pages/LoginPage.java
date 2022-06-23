package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAPI{
    Logger LOG = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[id='user-name']")
    private WebElement usernameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#login-button")
    private WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    //in this class I have to create all the object and the reusable steps

    public boolean checkUsernameFieldPresence(){
        boolean checkUsernameFieldPresence = isPresent(usernameField);
        LOG.info("username field presence: "+checkUsernameFieldPresence);
        return isPresent(usernameField);
    }

    public boolean checkPasswordFieldPresence(){
        boolean checkPasswordFieldPresence = isPresent(passwordField);
        LOG.info("password field presence: "+checkPasswordFieldPresence);
        return isPresent(passwordField);
    }

    public boolean checkLoginButtonPresence(){
        boolean checkLoginBtnPresence = isPresent(loginBtn);
        LOG.info("login button presence: "+checkLoginBtnPresence);
        return checkLoginBtnPresence;
    }

    public void enterUsername(String username){
        type(usernameField, username);
        LOG.info("username entered");
    }

    public void enterPassword(String password){
        type(passwordField, password);
        LOG.info("password entered");
    }

    public void clickOnLoginBtn(){
        click(loginBtn);
        LOG.info("click on login button success");
    }

    public String errorMessage(){
        String errorMessageText = getElementText(errorMessage);
        LOG.info("error message text: "+errorMessageText);
        return errorMessageText;
    }
}
