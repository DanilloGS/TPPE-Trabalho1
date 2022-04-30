package models;

public class  Dependente {

    public static double pensionValue = 189.59;

    private String name;
    private String birthDay;

    public Dependente(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthDay() {
        return this.birthDay;
    }
}