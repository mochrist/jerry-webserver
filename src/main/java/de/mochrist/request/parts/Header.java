package de.mochrist.request.parts;

public class Header {
    private String name;
    private String value;

    public Header(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Header parse(String line) {
        String[] parts = line.split(":", 2); //nur beim ersten : trennen
        if (parts.length != 2) {
            throw new IllegalArgumentException("Ungültige Header-Zeile: " + line);
        }
        String name = parts[0].trim();
        String value = parts[1].trim();
        return new Header(name, value);
    }
}
