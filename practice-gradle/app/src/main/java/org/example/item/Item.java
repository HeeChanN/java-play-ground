package org.example.item;

public class Item {

    private int cnt = 0;

    public int getCnt() {
        return cnt;
    }

    public void increaseCnt() {
        cnt++;
    }

    public void decreaseCnt() {
        cnt--;
    }

    public String toString() {
        return cnt + "";
    }
}
