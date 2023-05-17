package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
public class VerificationPage {
    private SelenideElement verificationCodeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public DashboardPage validVerify(DataHelper.VerificationCode code) {
        verificationCodeField.setValue(code.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}

