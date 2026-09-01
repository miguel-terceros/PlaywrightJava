import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

public class SlowingThingsDown {
    @Test
    public void firstPlayWrightScriptRefactoredNew() {
        try (var pw = Playwright.create();
             var browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000))) {
            Page page = browser.newPage();
            page.navigate(HOME_WEB);
            System.out.println(page.title());
        }

    }
}
