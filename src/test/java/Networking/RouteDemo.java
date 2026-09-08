package Networking;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.SAVINGS_WEB;

@UsePlaywright
public class RouteDemo {

    @Test
    void routeAbort(Page page, Browser browser) {

        // this line will disable all the JS
        browser.newContext(new Browser.NewContextOptions().setJavaScriptEnabled(false));

        page.route("**/*.{js}", route -> route.abort());

        page.navigate(SAVINGS_WEB);

        page.getByTestId("deposit").fill("10");
        assertThat(page.locator("#result")).not().isVisible();      // fail
    }

    @Test
    void routeFulfill(Page page) {

        page.route("**/*.pdf", route -> route.fulfill(new Route.FulfillOptions()
                .setStatus(404)
                .setContentType("text/plain")
                .setBody("Not Found!")
        ));

        page.navigate(SAVINGS_WEB);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Download Our Offer")).click();

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/replaced.png")));

        page.waitForURL("**/*.pdf");

        var body = page.locator("body");
        assertThat(body).hasText("Not Found!");
    }
}
