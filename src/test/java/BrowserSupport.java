import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

public class BrowserSupport {

    @Test
    public void browserSupport() {
        try (var pw = Playwright.create()) {
            List<BrowserType> browserTypes = List.of(pw.chromium(), pw.firefox(), pw.webkit());  // or Arrays.asList()

            for(var type : browserTypes) {
                Page page = type.launch().newPage(); // skip the try-with-resources
                page.navigate("https://www.whatsmybrowser.org/");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(type.name() + ".png")));
            }
        }
    }
}
