package org.example.item;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ItemTest {

    @Test
    void getTest() {
        // given
        Item item = new Item();

        // then
        assertThat(item.getCnt()).isEqualTo(0);
    }

    @Test
    void incrementTest() {
        // given
        Item item = new Item();

        // when
        item.increaseCnt();

        assertThat(item.getCnt()).isEqualTo(1);
    }
}
