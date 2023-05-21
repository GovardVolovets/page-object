package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV2;
import ru.netology.page.MoneyTransferPage;
import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneySecondToFirst() {
        var loginPage = new LoginPageV2();
        var cardInfo = DataHelper.getSecondCard();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var amount = "100";
        var secondCard = cardInfo;
        var initialFirstCardBalance = dashboardPage.getCardBalance(0);
        var initialSecondCardBalance = dashboardPage.getCardBalance(1);

        dashboardPage.transferFirstToSecondCard()
                .transferToCard(secondCard.getNumber(), amount);

        var expectedFirstCardBalance = initialFirstCardBalance + Integer.parseInt(amount);
        var expectedSecondCardBalance = initialSecondCardBalance - Integer.parseInt(amount);

        var actualFirstCardBalance = dashboardPage.getCardBalance(0);
        var actualSecondCardBalance = dashboardPage.getCardBalance(1);

        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    void shouldTransferMoneyFirstToSecond() {
        var loginPage = new LoginPageV2();
        var cardInfo = DataHelper.getFirstCard();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var amount = "100";
        var firstCard = cardInfo;
        var initialFirstCardBalance = dashboardPage.getCardBalance(0);
        var initialSecondCardBalance = dashboardPage.getCardBalance(1);

        dashboardPage.transferSecondToFirstCard()
                .transferToCard(firstCard.getNumber(), amount);

        var expectedFirstCardBalance = initialFirstCardBalance - Integer.parseInt(amount);
        var expectedSecondCardBalance = initialSecondCardBalance + Integer.parseInt(amount);

        var actualFirstCardBalance = dashboardPage.getCardBalance(0);
        var actualSecondCardBalance = dashboardPage.getCardBalance(1);

        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    void shouldTransferMoneySecondToFirstUnderLimit() {
        var loginPage = new LoginPageV2();
        var cardInfo = DataHelper.getSecondCard();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var moneyTransferPage = new MoneyTransferPage();

        var amount = "100000";
        var secondCard = cardInfo;

        dashboardPage.transferFirstToSecondCard()
                .transferToCard(secondCard.getNumber(), amount);

        moneyTransferPage.errorTransferNotification();
    }
}

