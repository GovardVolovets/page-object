package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement transferButtonFirst = $$("[data-test-id=action-deposit]").get(0);
    private SelenideElement transferButtonSecond = $$("[data-test-id=action-deposit]").get(1);
    private SelenideElement cardList = $("[data-test-id=dashboard]");

    public DashboardPage() {
    }

    public MoneyTransferPage transferFirstToSecondCard() {
        transferButtonFirst.click();
        return new MoneyTransferPage();
    }
    public MoneyTransferPage transferSecondToFirstCard() {
        transferButtonSecond.click();
        return new MoneyTransferPage();
    }

    public int getCardBalance(int cardIndex) {
        String text = cards.get(cardIndex).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value.replaceAll("\\s+", ""));
    }
}
