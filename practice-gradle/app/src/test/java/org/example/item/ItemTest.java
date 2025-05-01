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

    @Test
    void decrementTest() {
        Item item = new Item();

        item.decreaseCnt();

        assertThat(item.getCnt()).isEqualTo(-1);
    }

    @Test
    void equalsTest() {
        Item item = new Item();
        assertThat(item).isEqualTo(item);
    }

    @Test
    void setTest() {
        Item item = new Item();
        item.setCnt(2);
        assertThat(item.getCnt()).isEqualTo(2);
    }
}
