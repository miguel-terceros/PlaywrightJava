import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

public class FirstPwScript {

    @Test
    // Playwright > BrowserType > Browser > Page
    public void firstScript() {
        try (Playwright playwright = Playwright.create()) {
            BrowserType type = playwright.chromium();

            try (Browser browser = type.launch()) {
                Page page = browser.newPage();
                page.navigate("https://playwright.dev/java/");
                System.out.println(page.title());
            }
        }
    }

    @Test
    public void firstScriptRefactor() {
        try (var pw = Playwright.create(); var browser = pw.chromium().launch()) {
            Page page = browser.newPage();
            page.navigate("https://playwright.dev/java/");
            System.out.println(page.title());
        }
    }
}
