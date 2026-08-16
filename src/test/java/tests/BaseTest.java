package tests;

import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setup() {
        System.out.println("Запуск тестового набора");
    }
}
