package models;

public class Deducao {
    private double value;
    private String deducaoType;
    private String deducaoDescription;

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDeducaoType() {
        return this.deducaoType;
    }

    public void setDeducaoType(String deducaoType) {
        this.deducaoType = deducaoType;
    }

    public String getDeducaoDescription() {
        return this.deducaoDescription;
    }

    public void setDeducaoDescription(String deducaoDescription) {
        this.deducaoDescription = deducaoDescription;
    }
}