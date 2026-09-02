package Actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class PerformingChecks {
    @Test
    void performingChecks(Page page) {
        page.navigate(HOME_WEB);

        var textarea = page.locator("#textarea");

        assertThat(textarea).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(500));
        assertThat(textarea).not().isEditable();
        assertThat(textarea).isInViewport();

        assertThat(textarea).hasText("", new LocatorAssertions.HasTextOptions().setIgnoreCase(true));

        // hasValue() vs hastText()
        assertThat(textarea).hasValue("");              // for what you typed in
        assertThat(textarea).hasText("");      // for what is between tags, e.g <textarea> some text </textarea>

        var msg = "msg";
        page.getByRole(AriaRole.CHECKBOX).check();
        textarea.fill(msg);

        assertThat(textarea).hasValue(msg);
        assertThat(textarea).not().hasText(msg);              // fails, as it should {I modified it}
    }

}
