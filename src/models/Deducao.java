package models;

public class Deducao extends Monetary {
    private String deducaoType;

    public Deducao(double value, String deducaoType, String description) {
        super(value, description);
        this.deducaoType = deducaoType;
    }

    public String getDeducaoType() {
        return this.deducaoType;
    }
}