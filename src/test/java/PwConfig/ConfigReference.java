package PwConfig;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.net.URI;
import java.nio.file.Path;
import java.util.Map;

public class ConfigReference {

    public static void main(String[] args) {

        var pw = Playwright.create(new Playwright.CreateOptions().setEnv(Map.of("key1", "val1")));

        BrowserType chromium = pw.chromium();

        Browser browser = chromium.launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1_000)
                .setTimeout(10_000)         // max time for browser instance
                .setDownloadsPath(Path.of(URI.create("")))
                .setChannel("chrome")
        );

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setStorageState(null)      // for auth
                .setHttpCredentials(null)   // basic auth: username / pwd
                .setExtraHTTPHeaders(null)
                .setJavaScriptEnabled(false)
                .setBaseURL("...")
                .setLocale("de-DE")
                .setGeolocation(null)
                .setViewportSize(null)
        );

        context.addCookies(null);
        context.setDefaultNavigationTimeout(2000);
        context.setDefaultTimeout(3000);
    }
}
