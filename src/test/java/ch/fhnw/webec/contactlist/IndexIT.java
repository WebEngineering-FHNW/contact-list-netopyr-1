package ch.fhnw.webec.contactlist;

import ch.fhnw.webec.contactlist.pages.IndexPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexIT {

    @LocalServerPort
    private int port;

    private final WebDriver driver = new HtmlUnitDriver();

    @Test
    void initialPageShouldNotShowContactDetails() {
        // when
        final IndexPage page = IndexPage.to(driver, port);

        // then
        assertThat(page.getContactDetails()).isEmpty();
        assertThat(page.getPlaceHolder()).isNotEmpty();
    }

    @Test
    void clickingNameShouldShowContactDetails() {
        // given
        final IndexPage page = IndexPage.to(driver, port);

        // when
        page.getContactLinks().get(3).click();

        // then
        assertThat(page.getContactDetails()).isNotEmpty();
        assertThat(page.getPlaceHolder()).isEmpty();
    }
}
