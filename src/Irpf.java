import java.util.ArrayList;

public class Irpf {
    private ArrayList<Rendimento> rendimento = new ArrayList<>();

    void addRendimento(String description, int value){
        Rendimento _rendimento = new Rendimento();
        _rendimento.setValue(value);
        _rendimento.setDescription(description);
        this.rendimento.add(_rendimento);
    }

    ArrayList<Rendimento> getRendimento(){
        return this.rendimento;
    }
}
