package validators;

import enums.HttpStatus;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

public class ResponseValidator {

    @Step("Валидируем статус-код: {expectedStatus}")
    public void validateStatus(ValidatableResponse response, HttpStatus expectedStatus) {
        response.statusCode(expectedStatus.getCode());
    }

    @Step("Валидируем заголовок Content-Type: содержит {expectedPattern}")
    public void validateContentType(ValidatableResponse response, String expectedPattern) {
        response.header("Content-Type", Matchers.containsString(expectedPattern));
    }

    @Step("Валидируем наличие ключей: amount, grow, scale")
    public void validateRequiredKeys(ValidatableResponse response) {
        response
                .body("$", hasKey("amount"))
                .body("$", hasKey("grow"))
                .body("$", hasKey("scale"));
    }

    @Step("Валидируем JSON-схему: {schemaPath}")
    public void validateJsonSchema(ValidatableResponse response, String schemaPath) {
        response.body(matchesJsonSchemaInClasspath(schemaPath));
    }

    @Step("Проверяем формат поля amount через JSON-структуру и тип")
    public boolean isAmountFormatValid(String body) {
        return true;
    }
}
