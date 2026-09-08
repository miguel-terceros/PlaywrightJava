package PwConfig;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.SAVINGS_WEB;

@UsePlaywright
public class DefaultTimeoutDemo {

    @Test
    void defaultTimeout(BrowserContext context) {

        // actions - yes                (globally)
        // assertion - no
        context.setDefaultTimeout(1000);                            // higher-level settings loses

        // to affect assertions timeout (globally)
        PlaywrightAssertions.setDefaultAssertionTimeout(3000);      //  higher-level settings loses

        var page = context.newPage();
        page.navigate(SAVINGS_WEB);

        var deposit = page.getByTestId("deposit");
        var result = page.getByTestId("resultttt");

        deposit.fill("100"
                , new Locator.FillOptions().setTimeout(1000)        // overrides the higher-level config
        );

        assertThat(result).hasText("After 6 Months you will earn $2.00 on your deposit",
                new LocatorAssertions.HasTextOptions().setTimeout(2000)     // overrides the higher-level config
        );
    }

    @Test
    void newTest(Browser browser) {
        var context = newContext(browser);
    }

    public static BrowserContext newContext(Browser browser) {
        var context = browser.newContext(new Browser.NewContextOptions()    /* common config */);

        // more config
        context.setDefaultTimeout(10_000);
        return context;
    }
}
