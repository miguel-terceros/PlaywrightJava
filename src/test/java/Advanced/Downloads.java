package Advanced;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.SAVINGS_WEB;

@UsePlaywright
public class Downloads {

    @Test
    void downloadDemo(Playwright pw, Page page) {
        /*
        * Non-Previewable files, works in headed and headless mode
        * Previewable files, works only en headless mode (PDF)
        * */

//        var page = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        page.navigate(SAVINGS_WEB);

        Download download = page.waitForDownload( () -> {
            page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Download Our Offer")).click();
        });

        System.out.println(download.path());
        System.out.println(download.suggestedFilename());
    }
}
