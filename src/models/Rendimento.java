package models;

public class Rendimento {
    private String description;
    private double value;

    public Rendimento (double value, String description) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

}
