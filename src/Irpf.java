import exception.NonPositiveValueException;
import exception.VoidValueException;
import models.Rendimento;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Irpf {
    private ArrayList<Rendimento> rendimentos = new ArrayList<>();

    void addRendimento(String description, int value) throws VoidValueException, NonPositiveValueException {
        if(description == "")
            throw new VoidValueException("Descrção não pode ser vazia");
        if(value <= 0)
            throw new NonPositiveValueException("Valor deve ser positivo");
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
