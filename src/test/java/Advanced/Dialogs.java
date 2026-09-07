package Advanced;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.BUTTON;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class Dialogs {

    String name = "Sofia";

    @Test
    void dialogDefaultHandling(Page page) {
        page.navigate(HOME_WEB);

        var nameInput = page.getByLabel("First name");
        nameInput.fill(name);

        assertThat(nameInput).hasValue(name);

        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Clear")).click();
        assertThat(nameInput).hasValue(name);
    }

    @Test
    void dialogAccept(Page page) {

        page.onceDialog(Dialog::accept);    // page.onceDialog(dialog -> dialog.accept());

        page.navigate(HOME_WEB);

        var nameInput = page.getByLabel("First name");
        nameInput.fill(name);

        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Clear")).click();
        assertThat(nameInput).hasValue("");
    }
}
