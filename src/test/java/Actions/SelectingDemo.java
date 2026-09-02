package Actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.Constans.HOME_WEB;
import static org.example.Constans.SAVINGS_WEB;

@UsePlaywright
public class SelectingDemo {

    @Test
    void selectingTest(Page page) {
        page.navigate(SAVINGS_WEB);

        var deposit = page.getByTestId("deposit");
        var period = page.getByTestId("period");
        var result = page.getByTestId("result");

        deposit.fill("100");

        period.selectOption("6 months");
        assertThat(result).hasText("After 6 Months you will earn $2.00 on your deposit");

        period.selectOption(new SelectOption().setLabel("1 year"));
        assertThat(result).hasText("After 1 Year you will earn $5.00 on your deposit");
    }
}
