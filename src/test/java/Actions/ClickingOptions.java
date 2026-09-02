package Actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.MouseButton;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class ClickingOptions {

    @Test
    void clickingTest(Page page) {
        page.navigate(HOME_WEB);

        var button = page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register"));

        button.click();
        button.click();
        button.click();

        for (int i = 0; i < 5; i++) {
            button.click();
        }

        button.click(new Locator.ClickOptions().setClickCount(5));
        button.dblclick();

        button.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));

        button.click(new Locator.ClickOptions().setModifiers(Arrays.asList(KeyboardModifier.SHIFT)));
    }
}
