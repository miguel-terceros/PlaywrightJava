package Refactor;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.HOME_WEB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class AssertionsDemo {

    @Test
    void testWithPlaywrightAssertions(Page page) {
        page.navigate(HOME_WEB);

        // click "Register"

        var feedback = page.locator(".invalid-feedback");

        // JUnit assertions
        assertEquals("Credit Association", page.title());
        assertTrue(feedback.isVisible());

        // Playwright assertions
        assertThat(page).not().hasTitle("abcd");
        assertThat(feedback).isVisible();
    }
}
