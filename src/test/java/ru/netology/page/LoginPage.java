package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.domain.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id='login'] input");
    private SelenideElement password = $("[data-test-id='password'] input");
    private SelenideElement button = $("[data-test-id=action-login]");
    private SelenideElement error = $("[data-test-id='error-notification'] .notification__content");

    private void setUp (DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        setUp(info);
        return new VerificationPage();

    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        setUp(info);
        error.shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    public void invalidPassword(DataHelper.AuthInfo info) {
        setUp(info);
        error.shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    public void clearFields() {
        login.doubleClick().sendKeys(Keys.BACK_SPACE);
        password.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public void invalidPasswordThrice(DataHelper.AuthInfo info) {
        setUp(info);
        error.shouldBe(visible).shouldHave(text("Превышено число попыток входа!"));
    }

}
