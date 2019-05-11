import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;
import steps.SignUpSteps;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)


public class TestSignUp {

    @Steps
    SignUpSteps steps;

    @Managed(driver = "chrome")
    WebDriver driver;

    @Test
    public void typeInvalidYear() {
        steps.open_signup_page();
        steps.set_month("December");
        steps.set_day("20");
        steps.set_year("85");
        steps.set_share(true);
        steps.should_see_error("Please enter a valid year.");
        steps.should_not_see_error("When were you born");
    }

    @Test
    public void typeInvalidEmail() {
        steps.open_signup_page();
        steps.type_email("test@mail.test");
        steps.type_confirmation_email("wrong@mail.test");
        steps.type_name("Testname");
        steps.click_signup();
        steps.should_see_error("Email address doesn't match.");
    }

    @Test
    public void signUpWithEmptyPassword() {
        steps.open_signup_page();
        steps.type_email("test@mail.test");
        steps.type_confirmation_email("test@mail.test");
        steps.type_name("testname");
        steps.click_signup();
        steps.should_see_error("Please choose a password.");
    }

    @Test
    public void typeInvalidValues() {
        steps.open_signup_page();
        steps.type_email("testmail");
        steps.type_confirmation_email("wrong@test.mail");
        steps.type_password("qwerty!123");
        steps.type_name("Name");
        steps.select_sex("Male");
        steps.set_share(false);
        steps.click_signup();
        steps.should_see_errors_count(6);
        steps.should_see_error_by_number(3,"Please enter your birth month.");
    }
}