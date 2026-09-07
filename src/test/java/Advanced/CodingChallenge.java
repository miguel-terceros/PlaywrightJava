package Advanced;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.Constans.HOME_WEB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class CodingChallenge {

    @Test
    void codingChallenge(Page page) {

        page.navigate(HOME_WEB);

        // 1) create a list
        List<String> uncaughtErrors = new ArrayList<>();

        // 2) create the handler that adds errors to a list
        page.onPageError(uncaughtErrors::add);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();

        // 3) if list not empty = fail test (use plain JUnit assertion)
        assertFalse(uncaughtErrors.isEmpty(), "Expected list to be empty, but it contains: " + uncaughtErrors);

    }
}
