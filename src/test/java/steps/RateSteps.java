package steps;

import clients.CurrencyApiClient;
import enums.HttpStatus;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import validators.ResponseValidator;

public class RateSteps {

    private final CurrencyApiClient apiClient = new CurrencyApiClient();
    private final ResponseValidator validator = new ResponseValidator();

    @Step("Получаем ответ курса валюты и полностью валидируем его: {currencyCode}")
    public ValidatableResponse getAndValidateRate(String currencyCode) {
        Response response = apiClient.getRate(currencyCode);
        ValidatableResponse validatable = response.then();

        validator.validateStatus(validatable, HttpStatus.OK);
        validator.validateContentType(validatable, "application/json");
        validator.validateRequiredKeys(validatable);
        validator.validateJsonSchema(validatable, "schemas/rate_schema.json");

        return validatable;
    }

    @Step("Получаем тело ответа в виде строки (после валидации)")
    public String getResponseAsString(ValidatableResponse validatable) {
        return validatable.extract().asString();
    }
}
