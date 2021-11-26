package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.domain.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement button = $("[data-test-id='action-verify']");
    private SelenideElement error = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        button.click();
        return new DashboardPage();
    }

    public void invalidVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        button.click();
        error.shouldBe(visible).shouldHave(text("Неверно указан код! Попробуйте ещё раз."));
    }
}
