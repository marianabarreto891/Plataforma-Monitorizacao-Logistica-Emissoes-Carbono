package org.example;

public enum Mode {

    ROAD("road"),
    AIR("air"),
    SEE("see");

    private final String name;

    Mode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean matches (String mode) {
        return name.equals(mode);
    }
}
