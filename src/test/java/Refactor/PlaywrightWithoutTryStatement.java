package Refactor;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

public class PlaywrightWithoutTryStatement {

    @Test
    void playwrightWithoutTry() {
        var pw = Playwright.create();

        if (pw != null) {
            // ...
        }

        Page page = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();   // NPE ?

        page.navigate(HOME_WEB);

        pw.close();
    }
}
