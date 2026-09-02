package Actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.SAVINGS_WEB;

@UsePlaywright
public class TextAndAttributes {

    @Test
    void textAndAttributes(Page page) {
        page.navigate(SAVINGS_WEB);

        var form = page.locator(".needs-validation");

        System.out.println("====== Inner HTML ======");
        System.out.println(form.innerHTML());       // incl. HTML tags

        System.out.println("====== Text Content ======");
        System.out.println(form.textContent());     // incl. whitespaces

        System.out.println("====== Inner Text ======");
        System.out.println(form.innerText());       // incl. whitespace trimmed
    }
}
