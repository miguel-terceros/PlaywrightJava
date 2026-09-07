package Advanced;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class ConsoleEvents {

    @Test
    void consoleEvents(Page page) {

        page.onConsoleMessage(msg -> {
            System.out.printf("Console message found %s: %s%n", msg.type(), msg.text());
        });

        page.navigate(HOME_WEB);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
    }

    @Test
    void consoleErrors(Page page) {

        // LISTENER should be before the actions
        page.onPageError(err -> {
            System.out.println("Exception: " + err);
        });

        page.navigate(HOME_WEB);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
    }
}
