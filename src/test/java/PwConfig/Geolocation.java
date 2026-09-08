package PwConfig;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.microsoft.playwright.options.AriaRole.BUTTON;

@UsePlaywright
public class Geolocation {

    // GPS (mobile), Wi-fi data, IP address, etc.
    @Test
    void geolocationTest(Playwright pw) {
        var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        var page = browser.newContext(new Browser.NewContextOptions()
                .setLocale("en_GB")
                .setGeolocation(51.509865, -0.118092)
                .setPermissions(List.of("geolocation"))
                .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) " +
                        "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1;")


        ).newPage();


        page.navigate("https://maps.google.com");
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Accept all")).click();
    }
}
