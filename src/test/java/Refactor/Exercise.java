package Refactor;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;
import static org.junit.jupiter.api.Assertions.assertFalse;

@UsePlaywright
public class Exercise {

    @Test
    void before(){
        try (var pw = Playwright.create()) {
            BrowserType browserType = pw.chromium();

            try (Browser browser = browserType.launch()) {
                Page page = browser.newPage();
                page.navigate(HOME_WEB);

                page.fill("#firstName", "Some_name");
                page.click("#register");

                assertFalse(page.getByText("Valid first name is required").isVisible());
            }
        }
    }

    @Test
    void after(Page page) {
        page.navigate(HOME_WEB);

        page.getByLabel("First name").fill("some name");
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
        assertThat(page.getByText("Valid first name is required")).not().isVisible();
    }
}
