package Sob;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;


public class AuthorizationTest extends AuthorizationPage {

    @BeforeMethod
    public void beforeTests(){
        open("https://account.templatemonster.com/auth/");

        if(accountMenu.exists()){
            accountMenu.click();
            logout.click();
        }

        facebookSignInButton.shouldBe(Condition.visible);
    }

    @Test
    public void loginViaFacebook(){

        WebDriver driver = WebDriverRunner.getWebDriver();
        facebookSignInButton.click();

        Set handles = driver.getWindowHandles();
        Selenide.switchTo().window((String) handles.toArray()[1]);

        facebookEmailInput.shouldBe(Condition.visible);
        facebookEmailInput.val("emailfrom@facebook.com");
        facebookPassword.val("password1234");
        facebookLoginButton.click();

        Set handlesAfter = driver.getWindowHandles();
        assertThat("Window of Sign in via Facebook is not closed", handlesAfter.size()==1);
        assertThat("We still at login page", !facebookSignInButton.isDisplayed());
        assertThat("We don't route to main page", profileOnMainPage.isDisplayed());
    }

    @Test
    public void loginViaEmail(){
            inputEmailField.val("testemail@google.com");
            continueButton.click();
            inputPasswordField.val("password1234");
            loginButton.click();

        assertThat("We still at login page", !facebookSignInButton.isDisplayed());
        assertThat("We don't route to main page", profileOnMainPage.isDisplayed());
    }

    @Test
    public void creatingNewUserViaEmail(){
        inputEmailField.val("testemail@google.com");//we must put here email, that is not registered on Monster website
        continueButton.click();
        inputPasswordField.val("password1234");
        createAccountButton.click();

        String urlFromConfirmMessage = "get Api url from confirm message on email";
        open(urlFromConfirmMessage);

        //I had error like "it no possible do it now", but registration was success
        //so, i think that we must be route to https://account.templatemonster.com/#/downloads

        assertThat("We still at login page", !facebookSignInButton.isDisplayed());
        assertThat("We don't route to main page", profileOnMainPage.isDisplayed());
    }

    @Test
    public void negativeLoginWithWrongPassword(){
        inputEmailField.val("testemail@google.com");//correct email of registered user
        continueButton.click();
        inputPasswordField.val("4321drowssap");//wrong password
        loginButton.click();
        notificationContainer.shouldBe(Condition.visible).shouldHave(Condition.text("Wrong password for the account, please try again or  \nrecover your password"));
    }

    @Test
    public void negativeLoginWithShortPassword(){
        inputEmailField.val("testemail@google.com");//correct email of registered user
        continueButton.click();
        inputPasswordField.val("432");//short email
        notificationContainer.shouldBe(Condition.visible).shouldHave(Condition.text("Wrong password for the account, please try again or  \nrecover your password"));
    }
}
