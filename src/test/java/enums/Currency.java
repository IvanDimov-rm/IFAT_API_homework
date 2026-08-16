package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");

    private final String code;
}
