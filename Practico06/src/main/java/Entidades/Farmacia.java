package Entidades;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Farmacia {
    private Map<Medicamento, Integer> stock;

    public void restarMedicamentos(Medicamento medicamento, int cantidad) {
        if (stock.containsKey(medicamento)) {
            int stockActual = stock.get(medicamento);
            stock.put(medicamento, stockActual - cantidad);
        }
    }

    public void agregarMedicamentos(Medicamento medicamento, int cantidad) {
        if (stock.containsKey(medicamento)) {
            int stockActual = stock.get(medicamento);
            stock.put(medicamento, stockActual + cantidad);
        } else {
            stock.put(medicamento, cantidad);
        }
    }
}
