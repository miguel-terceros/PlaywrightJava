package Locators;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

public class OtherLocators {

    @Test
    void outdatedLocators() {
        try (var pw = Playwright.create();
            var browse = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000))) {
            Page page = browse.newPage();
            page.navigate(HOME_WEB);

            page.check("#heard-about");
            page.fill("#textarea", "Your message");

            // poor locator reusability
            // 2+ matches? -> default to 1st

            // IFRAMES -> check documentation
            var button = page.frameLocator("#bar").getByRole(AriaRole.BUTTON);
            button.click();


        }
    }
}
