package Locators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandlingMultipleMatches {

    @Test
    void multipleMatchesFails() {
        try (var pw = Playwright.create();
            var browser = pw.chromium().launch()) {
            Page page = browser.newPage();
            page.navigate(HOME_WEB);

            page.getByRole(LINK).click();  // Error: strict mode violation
//            page.getByRole(LINK, new Page.GetByRoleOptions().setName("Loans"));       <-- suggested by playwright to fix
        }
    }

    @Test
    void multipleMatchesFirstLastNth() {
        try (var pw = Playwright.create();
            var browser = pw.chromium().launch()) {
            Page page = browser.newPage();
            page.navigate(HOME_WEB);

            Locator buttons = page.getByRole(BUTTON);   // single obj that may contain many elements

            System.out.println(buttons.first().textContent());
            System.out.println(buttons.last().textContent());
            System.out.println(buttons.nth(1));
        }
    }

    @Test
    void multipleMatchesCountOrIterate() {
        try (var pw = Playwright.create();
             var browser = pw.chromium().launch()) {
            Page page = browser.newPage();
            page.navigate(HOME_WEB);

//            page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register")).click();

            var warnings = page.locator(".invalid-feedback");
            assertEquals(warnings.count(), 3);  // counts presence in HTML

            for (var message : warnings.all()) {
                assertTrue(message.isVisible());    // possible, but not recommended
            }
        }
    }
}
