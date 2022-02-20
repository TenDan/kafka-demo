package pl.tendan.kafkademo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

    @Test
    void shouldApplyDefaultCreationDate() {
        // when
        Message message = new Message();

        // then
        assertThat(message.getCreationDate())
                .isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Some content 1",
            "Some content 2",
            "Some content 3",
            "Some content 4",
            "Some content 5",
    })
    void shouldApplyValuesFromSetters(String candidate) {
        // given
        Message message = new Message();
        Date exampleDate = new Date(System.currentTimeMillis());

        // when
        message.setContent(candidate);
        message.setCreationDate(exampleDate);

        // then
        assertThat(message.getContent())
                .isNotNull()
                .isEqualTo(candidate);
        assertThat(message.getCreationDate())
                .isNotNull()
                .isEqualTo(exampleDate);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Some content 1",
            "Some content 2",
            "Some content 3",
            "Some content 4",
            "Some content 5",
    })
    void shouldApplyValuesFromAllArgsConstructor(String candidate) {
        // given
        Date exampleDate = new Date(System.currentTimeMillis());

        // when
        Message message = new Message(candidate, exampleDate);

        // then
        assertThat(message.getContent())
                .isNotNull()
                .isEqualTo(candidate);
        assertThat(message.getCreationDate())
                .isEqualTo(exampleDate);
    }

    @Test
    void shouldCompareTwoObjectsWithSameData() {
        // given
        Date exampleDate = new Date(System.currentTimeMillis());
        String title = "Same content";
        var message1 = new Message(title, exampleDate);
        var message2 = new Message(title, exampleDate);

        // when
        var actual1 = message1.equals(message2);
        var actual2 = message2.equals(message1);

        // then
        assertThat(actual1)
                .isTrue();
        assertThat(actual2)
                .isTrue();
    }

    @Test
    void shouldCompareTwoObjectsWithDifferentData() {
        // given
        String title = "Same content";
        var message1 = new Message(title, new Date(System.currentTimeMillis()));
        var message2 = new Message(title, new Date(System.currentTimeMillis() + 10));

        var actual1 = message1.equals(message2);
        var actual2 = message2.equals(message1);

        assertThat(actual1)
                .isFalse();
        assertThat(actual2)
                .isFalse();
    }
}