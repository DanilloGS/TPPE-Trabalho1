package models;

public class Deducao {
    private double value;
    private String deducaoType;
    private String deducaoDescription;

    public double getValue() {
        return 1000.00;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDeducaoType() {
        return deducaoType;
    }

    public void setDeducaoType(String deducaoType) {
        this.deducaoType = deducaoType;
    }

    public String getDeducaoDescription() {
        return "Contribuicao Previdencia Oficial";
    }

    public void setDeducaoDescription(String deducaoDescription) {
        this.deducaoDescription = deducaoDescription;
    }
}