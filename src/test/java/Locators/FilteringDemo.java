package Locators;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.SAVINGS_WEB;

public class FilteringDemo {

    @Test
    void filters() {
        try (var pw = Playwright.create();
            var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000))) {
            Page page = browser.newPage();
            page.navigate(SAVINGS_WEB);

            var rows = page.getByRole(ROW);
            System.out.println(rows.count());

            var row = rows.filter(text("Competition"));

            System.out.println(row.textContent());
        }
    }

    private static Locator.FilterOptions text(String str) {
        return new Locator.FilterOptions()
                .setHasText(str);
    }
}
