package Advanced;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class Screenshots {

    @Test
    void screenshots(Page page) {

        page.navigate(HOME_WEB);

        var nameInput = page.getByLabel("First name");
        nameInput.fill("Miguel");
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register").setExact(true)).click();

        // basic
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/screenshot.jpg")));

        // advanced
        var elementsToMask = page.locator(".form-control").all();
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/screenshot-advanced.jpg"))
                .setFullPage(true)              // take screenshot of all the page
                .setMask(elementsToMask)        // mask elements in the page
                .setMaskColor("blue"));         // give a color of the mask

        var feedback = page.locator(".invalid-feedback").all();
        for (var msg : feedback) {
            assertThat(msg).not().isVisible();      // fails
        }
    }
}
