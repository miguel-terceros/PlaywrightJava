package Refactor;

import org.example.TestScriptBase;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

public class PlaywrightWithoutTryStatementTags extends TestScriptBase {

    @Test
    void testOne() {

        page.navigate(HOME_WEB);
        System.out.println("Test 1: " + page.title());
    }

    @Test
    void testTwo() {

        page.navigate(HOME_WEB);
        System.out.println("Test 2: " + page.title());
    }
}
