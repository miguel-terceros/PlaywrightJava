package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestScriptBase {
    static Playwright pw;
    static Browser browser;
    protected static Page page;

    @BeforeAll
    static void setup() {
        pw = com.microsoft.playwright.Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void setupPage() {
        page = browser.newPage();
    }

    @AfterEach
    void cleanupPage() {
        page.close();
    }

    @AfterAll
    static void cleanup() {
        if(pw != null) {
            pw.close();
        }
    }
}
