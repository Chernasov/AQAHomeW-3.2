package ru.netology;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    private LoginPage loginPage = new LoginPage();

    @BeforeEach
    void setUpPage() {
        open("http://localhost:9999/");
    }

    @AfterAll
    static void deleteData() {
        DataHelper.clearAllData();
    }

    @Test
    void shouldTruePath() {
        var infoValidUser = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(infoValidUser);
        var verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldUseInvalidLogin() {
        var infoInvalidUserLogin = DataHelper.getInvalidLogin();
        loginPage.invalidLogin(infoInvalidUserLogin);
    }

    @Test
    void shouldUseInvalidPassword() {
        var infoInvalidUserPassword = DataHelper.getInvalidPassword();
        loginPage.invalidRassword(infoInvalidUserPassword);
    }

    @Test
    void shouldUseInvalidPasswordThrice() {
        var infoInvalidUserPassword1 = DataHelper.getInvalidPassword();
        loginPage.invalidRassword(infoInvalidUserPassword1);
        loginPage.clearFields();
        var infoInvalidUserPassword2 = DataHelper.getInvalidPassword();
        loginPage.invalidRassword(infoInvalidUserPassword2);
        loginPage.clearFields();
        var infoInvalidUserPassword3 = DataHelper.getInvalidPassword();
        loginPage.invalidRasswordThrice(infoInvalidUserPassword3);
    }

    @Test
    void shouldUseInvalidVerificationCode() {
        var infoValidUser = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(infoValidUser);
        var invalidVerificationCode = DataHelper.getInvalidVerificationCodeFor();
        verificationPage.invalidVerify(invalidVerificationCode);
    }
}
