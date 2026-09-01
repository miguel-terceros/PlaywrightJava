package Locators;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericLocators {

    @Test
    public void cssAndXpathDemo() {
        try (var pw = Playwright.create();
            var browser =pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000))) {
            Page page = browser.newPage();
            page.navigate(HOME_WEB);

            page.locator(".needs-validation label[for='firstName']").fill("Miguel");
            page.locator("//form//button[2]").click();

            assertTrue(page.getByText("Valid last name is required").isVisible());
        }
    }
}
