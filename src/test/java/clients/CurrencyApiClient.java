package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.PropertyReader;

public class CurrencyApiClient {

    private static final String BASE_URL = PropertyReader.getProperty("currency.url");

    public Response getRate(String currencyCode) {
        return RestAssured.given()
                .when()
                .queryParam("currency", currencyCode)
                .get(BASE_URL)
                .then()
                .extract()
                .response();
    }
}
