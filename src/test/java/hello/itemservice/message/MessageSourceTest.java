package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @DisplayName("1. helloMessage")
    @Test
    void test_1() {
        String result = ms.getMessage("hello", null, null);
        assertEquals(result, "안녕");
    }

    @DisplayName("2. notFoundMessageCode")
    @Test
    void test_2() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @DisplayName("3. notFoundMessageCodeDefaultMessage")
    @Test
    void test_3() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertEquals(result, "기본 메시지");
    }

    @DisplayName("4. argumentMessage")
    @Test
    void test_4() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertEquals(message, "안녕 Spring");
    }

    @DisplayName("5. defaultLang")
    @Test
    void test_Count() {
        assertEquals(ms.getMessage("hello", null, null), "안녕");
        assertEquals(ms.getMessage("hello", null, Locale.KOREA), "안녕");
    }

    @DisplayName("6. enLang")
    @Test
    void test_6() {
        assertEquals(ms.getMessage("hello", null, Locale.ENGLISH), "hello");
    }

}
