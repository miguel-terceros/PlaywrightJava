package Networking;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static org.example.Constans.HOME_WEB;

@UsePlaywright
public class RequestResponseOverview {

    @Test
    void responseApiDemo(Page page) {
        Response res = page.navigate(HOME_WEB);     // or any public website

        System.out.println(res.url());
        System.out.println(res.status());
        System.out.println(res.ok());
        System.out.println(res.headers());

        System.out.println(res.body());     // hash
        System.out.println(res.text());
    }

    @Test
    void requestApiDemo(Page page) {

        Response res = page.navigate(HOME_WEB);
        Request request = res.request();

        System.out.println(request.headers());
        System.out.println(request.postData());
        System.out.println(request.method());
    }
}
