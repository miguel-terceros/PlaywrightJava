package Networking;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@UsePlaywright
public class MixedE2ETests {

    APIRequestContext apiContext;

    String repoName = "Playwright-test-repo";
    String username = "your_username";
    String token = "your_token";

    @BeforeEach
    void createRepo(Playwright pw) {

        apiContext = pw.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://api.github.com/")
                .setExtraHTTPHeaders(Map.of(
                        "Accept", "application/vnd.github.v3+json",
                        "Authorization", "token " + token
                ))
        );


        var response = apiContext.post("user/repos", RequestOptions.create()
                .setData(String.format("{\"name\": \"%s\"}", repoName))
        );

        assertThat(response).isOK();

    }

    @Test
    void workWithNewlyCreatedRepo(Page page) {

        page.navigate(String.format("https://github.com/%s?tab=repositories", username));

        var newRepo = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(repoName));

        assertEquals(1, newRepo.count());

        // more actions

    }

    @AfterEach
    void deleteRepo() {

        var response = apiContext.delete(String.format("repos/%s/%s", username, repoName));
        assertEquals(204, response.status(), "Failed to delete repository " + repoName);
    }
}
