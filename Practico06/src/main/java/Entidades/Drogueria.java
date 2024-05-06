package Entidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Drogueria {
    public Map<Medicamento, Integer> proporcionarMedicamentos(Map<Medicamento, Integer> pedido) {
        return pedido;
    }
}
