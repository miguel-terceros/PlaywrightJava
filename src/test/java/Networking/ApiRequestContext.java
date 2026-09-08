package Networking;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class ApiRequestContext {

    @Test
    void uiToHttp(Page page) {

        // 1 UI <--> HTTP
        Response response = page.navigate(HOME_WEB);    // + .reload(), goForward(), .goBack()
        Request request = response.request();

        // 2 UI <--> HTTP
        page.onRequest(null);
        page.onResponse(null);
    }

    @Test
    void pureHttp(APIRequestContext apiCtx) {

        APIResponse apiResponse = apiCtx.get("https://api.github.com/");


        System.out.println(apiResponse.url());
        System.out.println(apiResponse.text());  // JSON

        PlaywrightAssertions.assertThat(apiResponse).isOK();
    }

    void hierarchy(Playwright pw, Browser br, BrowserContext ctx, Page page) { }

    @Test
    void pureHttpWithContext(Playwright pw) {
        APIRequestContext ctx = pw.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("")
                        .setExtraHTTPHeaders(null)
                        .setHttpCredentials(null)
                        .setStorageState("")
        );

        ctx.get("request 1");
        ctx.get("request 2");
        ctx.get("request 3...");
    }
}
