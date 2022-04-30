package models;

public abstract class Monetary {
    protected double value;
    protected String description;

    public Monetary(double value, String description) {
        this.value = value;
        this.description = description;
    }

    public double getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }


}
