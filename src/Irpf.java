import java.util.ArrayList;

public class Irpf {
    private Rendimento rendimento = new Rendimento();

    void addRendimento(String description, int value){
        this.rendimento.setValue(value);
        this.rendimento.setDescription(description);
    }

    Rendimento getRendimento(){
        return this.rendimento;
    }
}
