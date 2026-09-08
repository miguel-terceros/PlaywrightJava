package PwConfig;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class BrowserConfigDemo {

    @Test
    void browserConfigDemo(Playwright pw) {

        // chromium = browser engine
        // Chrome & MS Edge = build on top of chromium

        var br = pw.chromium().launch(config());
        var br2 = pw.chromium().launch(config().setChannel("chrome"));
        var br3 = pw.chromium().launch(config().setChannel("msedge"));

        br.newPage().navigate(HOME_WEB);
        br2.newPage().navigate(HOME_WEB);
        br3.newPage().navigate(HOME_WEB);
    }

    private static BrowserType.LaunchOptions config() {return new BrowserType.LaunchOptions().setHeadless(false); }
}
