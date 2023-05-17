package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage transferToCard(String cardNumber, String amount) {
        amountField.click();
        amountField.setValue(amount);
        cardNumberField.click();
        cardNumberField.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }
}
