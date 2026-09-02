package Actions;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class CodingChallenge {

    @Test
    void codingChallenge(Playwright pw) {
        var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        var page = browser.newPage();
        page.navigate(HOME_WEB);

        String message = "msg";
        var checkbox = page.getByRole(CHECKBOX);
        var textarea = page.locator("#textarea");

        // check the checkbox
        checkbox.check();

        // fill in the text area
        textarea.fill(message);

        // click "Save Input"
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Save Input")).click();

        // reload
        page.reload();

        // assert that the checkbox remains checked and textarea filled in
        assertThat(checkbox).isChecked();
        assertThat(textarea).hasValue(message);
    }
}
