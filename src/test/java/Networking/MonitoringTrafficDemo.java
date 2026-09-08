package Networking;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.Constans.HOME_WEB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright
public class MonitoringTrafficDemo {

    @Test
    void printTrafficDemo(Page page) {

        page.onRequest(request -> {
            System.out.println(">> " + request.method() + " " + request.url());
        });

        page.onResponse(response -> {
            System.out.println("<< " + response.status());
        });

        page.navigate(HOME_WEB);
    }

    @Test
    void assertTrafficDemo(Page page) {
        // check no status code is outside 200-209 range

        List<Integer> statusCodes = new ArrayList<>();
        page.onResponse(response -> statusCodes.add(response.status()));

        page.navigate(HOME_WEB);
        System.out.println(statusCodes);

        boolean foundMatch = statusCodes.stream()
                .anyMatch(code -> code < 200 || code >= 300);

        assertFalse(foundMatch);
    }

//    org.opentest4j.AssertionFailedError: Found responses with codes outside 200 range:
//            404: http://localhost:8000/js/non-existing-script.js
//            404: http://localhost:8000/js/non-existing-script2.js ==>
//    Expected :true
//    Actual   :false

    @Test
    void assertTrafficChallenge(Page page) {

        // check traffic using the .ok() method
        List<Response> responses = new ArrayList<>();
        page.onResponse(responses::add);

        page.navigate(HOME_WEB);
        var nonOkResponses = responses.stream()
                .filter(response -> !response.ok())
                .toList();

        // assert no "false" found
        assertTrue(nonOkResponses.isEmpty(), "Found responses with codes outside 200 range: \n" + pretty(nonOkResponses));

    }

    static String pretty(List<Response> list) {
        return list.stream()
                .map(response -> String.format("%d: %s", response.status(), response.url()))
                .collect(Collectors.joining("\n"));
    }
}
