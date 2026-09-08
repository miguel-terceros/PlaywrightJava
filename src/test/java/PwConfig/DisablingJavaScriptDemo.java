package PwConfig;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.SAVINGS_WEB;

@UsePlaywright
public class DisablingJavaScriptDemo {

    @Test
    void javascriptDisabled(Playwright pw) {
        var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // disable JS in context
        var page = browser.newContext(new Browser.NewContextOptions().setJavaScriptEnabled(false)).newPage();

        // navigate
        page.navigate(SAVINGS_WEB);

        // check message is visible
        var warning = page.locator("#warning");
        assertThat(warning).isVisible();
    }
}
