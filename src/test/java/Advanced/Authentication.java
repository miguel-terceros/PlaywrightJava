package Advanced;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class Authentication {

    @Test
    void authDemoCreate(Playwright pw) {
        var context = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext();
        var page = context.newPage();

        page.navigate("https://github.com/login");

        page.locator("#login_field").fill("miguel-terceros");
        page.locator("#password").fill("enterPassword");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in").setExact(true)).click();

//        page.getByPlaceholder("XXXXXX").fill("some num here");

        assertThat(page.getByLabel("Open user navigation menu")).isVisible();

        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
    }

    @Test
    void authDemoUse(Browser browser) {

        // create a new context with the saved storage state
        var context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("state.json")));

        var page = context.newPage();
        page.navigate("https://github.com");

        // verify already logged in
        assertThat(page.getByLabel("Open user navigation menu")).isVisible();
    }
}
