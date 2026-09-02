package Actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class CheckingDemo {

    @Test
    void checkingTest(Page page) {
        page.navigate(HOME_WEB);

        String message = "msg";

        var checkbox = page.getByRole(AriaRole.CHECKBOX);
        var textarea = page.locator("#textarea");

        checkbox.check();
        textarea.fill(message);

        assertThat(textarea).hasValue(message);
    }
}
