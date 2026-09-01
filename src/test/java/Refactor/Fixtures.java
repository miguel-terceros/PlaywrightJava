package Refactor;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class Fixtures {

    @Test
    void testWithPageFixture(Page page) {
        page.navigate(HOME_WEB);
        Assertions.assertEquals("Credit Association", page.title());
    }

    @Test
    void testWithBrowserFixture(Browser browser) {
        var context = browser.newContext(new Browser.NewContextOptions().setLocale("...").setBaseURL(HOME_WEB));
        var page = context.newPage();

        page.navigate("");
    }
}
