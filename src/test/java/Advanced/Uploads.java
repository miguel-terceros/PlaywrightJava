package Advanced;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.LOANS_WEB;

@UsePlaywright
public class Uploads {

    @Test
    void uploadFileDemo(Playwright pw) {
        var page = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        page.navigate(LOANS_WEB);

        // select one file
        var uploadButton = page.locator("//input[@type='file']");
        uploadButton.setInputFiles(Paths.get("./web/files/dummy.pdf"));

        // the website in use, creates a fake path
        assertThat(uploadButton).hasValue("C:\\fakepath\\dummy.pdf");
    }
}
