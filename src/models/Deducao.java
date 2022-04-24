package models;

public class Deducao {
    private double value;
    private String deducaoType;
    private String deducaoDescription;

    public Deducao(double value, String deducaoType, String deducaoDescription) {
        this.value = value;
        this.deducaoType = deducaoType;
        this.deducaoDescription = deducaoDescription;
    }

    public double getValue() {
        return this.value;
    }

    public String getDeducaoType() {
        return this.deducaoType;
    }

    public String getDeducaoDescription() {
        return this.deducaoDescription;
    }

}