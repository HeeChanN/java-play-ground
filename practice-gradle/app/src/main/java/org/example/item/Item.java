package org.example.item;

import java.util.ArrayList;
import java.util.List;

public class Item {

    List<String> names = new ArrayList<String>();

    public void addName(String name) {
        names.add(name);
    }
}
