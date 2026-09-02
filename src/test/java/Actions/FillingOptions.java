package Actions;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class FillingOptions {

    @Test
    void fillingTest(Page page) {
        page.navigate(HOME_WEB);

        page.getByLabel("First name").fill("Miguel");

        page.getByLabel("Date of birth").fill("2026-09-01");

    }

    @Test
    void forcingTest(Playwright pw) {
        var page = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)).newPage();
        page.navigate(HOME_WEB);

        var button = page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register"));
        var textarea = page.locator("#textarea");

        button.click(new Locator.ClickOptions());

        textarea.fill("test", new Locator.FillOptions().setForce(true));    // bypass the internal checks
    }
}
