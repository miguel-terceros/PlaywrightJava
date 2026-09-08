package PwConfig;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.ViewportSize;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class ViewportDemo {

    @Test
    void viewportSizeDemo(Playwright pw) throws InterruptedException {
        var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        var ctx = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(500, 500)
                // or
                .setViewportSize(ViewportSizes.IPHONE_X)
                .setUserAgent("Mozilla/5.0 (Linux; Android 11; SM-G991B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Mobile Safari/537.36")
                .setDeviceScaleFactor(3)
                .setIsMobile(true)
                .setHasTouch(true)
        );

        var ctx2 = browser.newContext(Devices.iphoneX());
        var ctx3 = browser.newContext(DevicesEnum.IPHONE_X.get());


        var page = ctx.newPage();
        page.navigate(HOME_WEB);

        Thread.sleep(2000);
    }

    static class ViewportSizes {
        public static final ViewportSize IPHONE_X = new ViewportSize(375, 812);
        public static final ViewportSize GALAXY_S5 = new ViewportSize(360, 640);
    }

    static class Devices {
        private static Browser.NewContextOptions base() {
            return new Browser.NewContextOptions().setIsMobile(true).setHasTouch(true);
        }

        static Browser.NewContextOptions iphoneX() {
            return base()
                    .setViewportSize(375, 812)
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1");
        }
    }

    public enum DevicesEnum {
        IPHONE_X(375, 812, "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1");

        private final int width;
        private final int height;
        private final String userAgent;

        // Constructor for the enum
        DevicesEnum(int width, int height, String userAgent) {
            this.width = width;
            this.height = height;
            this.userAgent = userAgent;
        }

        // Common base options
        private Browser.NewContextOptions base() {
            return new Browser.NewContextOptions()
                    .setIsMobile(true)
                    .setHasTouch(true);
        }

        public Browser.NewContextOptions get() {
            return base()
                    .setViewportSize(width, height)
                    .setUserAgent(userAgent);
        }
    }
}
