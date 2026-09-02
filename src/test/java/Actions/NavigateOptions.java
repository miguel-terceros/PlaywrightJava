package Actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.*;
import static com.microsoft.playwright.options.WaitUntilState.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class NavigateOptions {

    @Test
    void navigateOptions(Page page) {
        page.navigate(HOME_WEB, new Page.NavigateOptions().setTimeout(5).setWaitUntil(LOAD));

        assertThat(page).hasTitle("Credit Association");
    }

    @Test
    void otherNavigateOptions(Page page) {
        page.navigate(HOME_WEB, new Page.NavigateOptions().setTimeout(5));

        assertThat(page).hasTitle("Credit Association");

        page.reload();
        page.goBack();
        page.goForward();
    }

    @Test
    void challengeTest(Page page) {
        page.navigate(HOME_WEB);

        // click register
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register")).click();

        // check "invalid feedback is visible"
        var feedback = page.locator(".invalid-feedback").all();

        for (var message : feedback) {
            assertThat(message).isVisible();
        }

        // reload
        page.reload();

        // check "invalid feedback is NOT visible"
        for (var message : feedback) {
            assertThat(message).not().isVisible();
        }
    }
}
