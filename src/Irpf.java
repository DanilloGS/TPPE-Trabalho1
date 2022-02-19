import java.util.ArrayList;

public class Irpf {
    private Rendimento rendimento = new Rendimento();

    void addRendimento(String description, int value){
        Rendimento rendimento = new Rendimento();
        rendimento.setValue(value);
        rendimento.setDescription(description);
    }

    Rendimento getRendimento(){
        return rendimento;
    }
}
