package Advanced;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.example.StorageState;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.AriaRole.*;
import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class BrowserStorage {

    String name = "Miguel";

    @Test
    void storageUiPerspective(Page page) {

        page.navigate(HOME_WEB);

        var nameInput = page.getByLabel("First name");
        nameInput.fill(name);
        page.reload();
        assertThat(nameInput).hasValue("");

        nameInput.fill(name);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Save Input")).click();
        page.reload();
        assertThat(nameInput).hasValue(name);
    }

    @Test
    void localStorage(Page page) {
        page.navigate(HOME_WEB);
        page.getByLabel("First name").fill(name);
        page.getByLabel("Last name").fill("Powers");
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Save Input")).click();

        String storageJson = page.context().storageState();
        System.out.println(storageJson);
    }

    @Test
    void localStorageJson(Page page) throws JsonProcessingException {
        page.navigate(HOME_WEB);
        page.getByLabel("First name").fill(name);
        page.getByLabel("Last name").fill("Powers");
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Save Input")).click();

        String storageJson = page.context().storageState();
        System.out.println(storageJson);

        // JSON to POJO
        StorageState statePojo = new ObjectMapper().readValue(storageJson, StorageState.class);

        for (var origin : statePojo.origins()) {
            System.out.printf("Origin: %s%n", origin.origin());

            for (StorageState.LocalStorageEntry entry : origin.localStorage()) {
                System.out.printf("%s : %s%n", entry.name(), entry.value());
            }
        }
    }

    @Test
    void usingJavaScript(Page page) {

        page.navigate(HOME_WEB);

        var nameInput = page.getByLabel("First name");
        nameInput.fill(name);
        page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Save Input")).click();

        var href = (String) page.evaluate("location.href");
        System.out.println(href);

        var storage = (Map<String, String>) page.evaluate("localStorage");
        System.out.println(storage);

        // manipulating the data
         page.evaluate("localStorage.clear()");
         page.reload();
         assertThat(nameInput).hasValue("");

         page.evaluate("localStorage.setItem('firstName', 'Sofia')");
         page.reload();
         assertThat(nameInput).hasValue("Sofia");
    }
}
