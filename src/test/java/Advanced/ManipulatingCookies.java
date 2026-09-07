package Advanced;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.Cookie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class ManipulatingCookies {

    @Test
    void cookies(Page page) {

        page.navigate(HOME_WEB);
        BrowserContext context = page.context();
        System.out.println(context.cookies());

        Cookie cookie = new Cookie("cookie1", "abc").setUrl("https://playwright.dev/");
        context.addCookies(List.of(cookie));

        System.out.println(context.cookies().getFirst());   // obj hash, no String()
        System.out.println(context.cookies().getFirst().name);

        context.clearCookies();

        System.out.println(context.cookies());
    }
}
