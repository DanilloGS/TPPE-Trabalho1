import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Irpf {
    private ArrayList<Rendimento> rendimentos = new ArrayList<>();

    void addRendimento(String description, int value){
        Rendimento _rendimento = new Rendimento();
        _rendimento.setValue(value);
        _rendimento.setDescription(description);
        this.rendimentos.add(_rendimento);
    }

    public ArrayList<Rendimento> getRendimentos(){
        return this.rendimentos;
    }

    public int getRendimentoTotal () {
        AtomicInteger totalValue = new AtomicInteger();
        this.rendimentos.forEach(rendimento ->
                totalValue.addAndGet(rendimento.getValue())
        );
        return totalValue.intValue();
    }
}
