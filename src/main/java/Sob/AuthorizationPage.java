package Sob;

import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriverService;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {

    SelenideElement facebookSignInButton = $("button.button_bg_facebook");
    SelenideElement facebookEmailInput = $("#email_container .inputtext");
    SelenideElement facebookPassword = $("#pass");
    SelenideElement facebookLoginButton = $("input[value=\"Log In\"]");

    SelenideElement profileOnMainPage = $("#top-panel-to-profile-page-link");
    SelenideElement accountMenu = $("#app-account-menu button");
    SelenideElement logout = $("button[aria-label=\"Sign Out\"]");
    SelenideElement inputEmailField = $(".id-general-email-field input");
    SelenideElement inputPasswordField = $(".id-general-password-field input");
    SelenideElement createAccountButton = $("#id-create-new-account");
    SelenideElement continueButton = $("#id-continue-login-button");
    SelenideElement loginButton = $("#id-password-login-button");
    SelenideElement notificationContainer = $(".notification__container");



    public AuthorizationPage(){
        System.setProperty("selenide.browser", "Chrome");
        ChromeDriverManager.getInstance().version("63.0.3239.84").setup();

    }
}
