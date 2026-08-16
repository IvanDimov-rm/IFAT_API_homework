package tests;

import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.RateSteps;
import validators.ResponseValidator;

import static enums.Currency.*;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.assertTrue;

@Epic("Интернет-сайт Лучшие курсы валют")
@Feature("Главная страница")
@Owner("Tamara Iutina uytinabp@gmail.com")
public class OnlinerTest extends BaseTest {

    private final RateSteps steps = new RateSteps();
    private final ResponseValidator validator = new ResponseValidator();

    @DataProvider(name = "currencies")
    public Object[][] currencyProvider() {
        return new Object[][]{
                {RUB.getCode()},
                {USD.getCode()},
                {EUR.getCode()}
        };
    }

    @Story("Проверка курса валют")
    @Severity(NORMAL)
    @Test(dataProvider = "currencies")
    public void checkRates(String currencyCode) {
        ValidatableResponse response = steps.getAndValidateRate(currencyCode);

        String body = steps.getResponseAsString(response);
        boolean isValid = validator.isAmountFormatValid(body);
        assertTrue(
                isValid,
                "Поле amount не соответствует ожидаемому формату для валюты: " + currencyCode
        );
    }
}
