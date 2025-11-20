package org.example;

public enum Geography {

    GLOBAL("global"),
    EUA("eua"),
    USA("usa");


    private final String name;

    Geography(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean matches (String geography) {
        return name.equals(geography);
    }
}
