package Locators;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

public class RecommendedLocators {

    @Test
    public void recommendedLocators() {
        try (var pw = Playwright.create();
            var browser = pw.chromium().launch(getLaunchOptions())) {
            Page page = browser.newPage();
            page.navigate(HOME_WEB);

            Locator name = page.getByLabel("First name");   // no interaction yet
            name.fill("Sofia");
            name.clear();

            page.getByLabel("First name").fill("Andrejs");

            page.getByRole(BUTTON, getName("Register")).click();

            var warning = page.getByText("Valid last name is required");
            Assertions.assertTrue(warning.isVisible()); // possible but not recommended!


        }
    }

    private static BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(1000);
    }

    // move to public utility class
    public static Page.GetByRoleOptions getName(String name) {
        return new Page.GetByRoleOptions()
                .setName(name);
    }
}
