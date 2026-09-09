package Tools;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class TraceViewer {

    Playwright pw;
    Browser browser;
    BrowserContext context;

    @Test
    void traceViewerDemo() {
        pw = Playwright.create();
        browser = pw.chromium().launch();

        // ---------------- Start capturing ----------------
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
        );

        Page page = context.newPage();
        page.navigate("https://playwright.dev/java/");
        page.getByRole(AriaRole.LINK, name("Get started")).click();
        page.getByRole(AriaRole.LINK, name("Trace viewer")).nth(1).click();

        var title = page.getByRole(AriaRole.HEADING, name("Trace viewerrrr").setExact(true));
        assertThat(title).isVisible();      // stopped here
    }

    static Page.GetByRoleOptions name(String name) {
        return new Page.GetByRoleOptions().setName(name);
    }

    @AfterEach
    void cleanup() {

        // --------------- Finish capturing -----------------
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip"))
        );

        pw.close();
    }
}
