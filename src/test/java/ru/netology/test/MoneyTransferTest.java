package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV2;
import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyFirstToSecond() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var amount = "10000";
        var secondCard = "5559 0000 0000 0002";
        dashboardPage.transferFirstToSecondCard()
                .transferToCard(secondCard, amount);

        var firstCardBalance = dashboardPage.getCardBalance(0);
        var secondCardBalance = dashboardPage.getCardBalance(1);

        Assertions.assertEquals(dashboardPage.getCardBalance(0), firstCardBalance);
        Assertions.assertEquals(dashboardPage.getCardBalance(1), secondCardBalance);
    }

    @Test
    void shouldTransferMoneySecondToFirst() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var amount = "10000";
        var firstCard = "5559 0000 0000 0001";
        dashboardPage.transferSecondToFirstCard()
                .transferToCard(firstCard, amount);

        var firstCardBalance = dashboardPage.getCardBalance(0);
        var secondCardBalance = dashboardPage.getCardBalance(1);

        Assertions.assertEquals(dashboardPage.getCardBalance(0), firstCardBalance);
        Assertions.assertEquals(dashboardPage.getCardBalance(1), secondCardBalance);
    }
}

