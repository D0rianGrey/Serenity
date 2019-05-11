package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

@DefaultUrl("https://www.spotify.com/us/signup/")
public class SignUpPage extends PageObject {

    private By emailField = By.xpath("//*[@id=\"register-email\"]");
    private By confirmEmailField = By.xpath("//*[@id=\"register-confirm-email\"]");
    private By passwordField = By.xpath("//*[@id=\"register-password\"]");
    private By nameField = By.xpath("//*[@id=\"register-displayname\"]");
    private By monthDropDown = By.xpath("//*[@id=\"register-dob-month\"]");
    private String monthDropDownOption = "//select[@id='register-dob-month']/option[text()='%s']";
    private By dayField = By.xpath("//*[@id=\"register-dob-day\"]");
    private By yearField = By.xpath("//*[@id=\"register-dob-year\"]");
    private String sexRadioButton = "//*[@id=\"li-gender\"]/label[normalize-space()='%s']";
    private By shareCheckbox = By.xpath("//*[@id=\"register-thirdparty\"]");
    private By registerButton = By.xpath("//*[@id=\"register-button-email-submit\"]");
    private By errorLabel = By.xpath("//label[@class=\"has-error\" and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";

    public SignUpPage typeEmail(String email) {
        find(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        find(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        find(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String name) {
        find(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage setMonth(String month) {
        find(monthDropDown).click();
        find(By.xpath(String.format(monthDropDownOption, month))).waitUntilVisible().click();
        return this;
    }

    public SignUpPage typeDay(String day) {
        find(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        find(yearField).sendKeys(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        find(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) { // есть true - чекаем, если false - то анчекаем
        WebElement checkbox = find(shareCheckbox);
        if (!checkbox.isSelected() == value) { // если не чекнуто - мы чекаем, если уже ченуто то оставляем как есть
            checkbox.click();
        }
        return this;
    }

    public void clickSignUpButton() {
        find(registerButton).click();
    }

    public List<WebElementFacade> getErrors() {
        return findAll(errorLabel);
    }

    public String getErrorByNumber(int number) {
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message) {
        return findAll(By.xpath(String.format(errorByText, message))).size() > 0
                && findAll(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }
}