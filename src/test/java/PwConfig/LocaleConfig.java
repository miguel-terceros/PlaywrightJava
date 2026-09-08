package PwConfig;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;

@UsePlaywright
public class LocaleConfig {

    @Test
    void localeChange(Playwright pw) {
        var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        var page = browser.newContext(new Browser.NewContextOptions()
                .setLocale("es-ES")
                .setTimezoneId("...")
                .setGeolocation(null)
        ).newPage();

        page.navigate("https://google.com");

        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Aceptar todo")).click();
    }
}
