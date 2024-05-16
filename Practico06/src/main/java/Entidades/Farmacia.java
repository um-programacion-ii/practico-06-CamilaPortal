package Entidades;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor


public class Farmacia {
    private List<Medicamento> stock;

    public Farmacia() {
        this.stock = new ArrayList<>();
    }

    public void restarMedicamentos(Medicamento medicamento, int cantidad) {
        for (Medicamento m : stock) {
            if (m.equals(medicamento)) {
                m.reducirCantidad(cantidad);
                return;
            }
        }
    }

    public void agregarMedicamento(Medicamento nuevoMedicamento) {
        boolean encontrado = false;
        for (Medicamento m : stock) {
            if (m.equals(nuevoMedicamento)) {
                m.aumentarCantidad(nuevoMedicamento.getCantidad());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            stock.add(nuevoMedicamento);
        }
    }

    public boolean tieneSuficienteStock(Medicamento medicamento) {
        for (Medicamento m : stock) {
            if (m.equals(medicamento)) {
                return m.getCantidad() >= medicamento.getCantidad();
            }
        }
        return false;
    }
}
