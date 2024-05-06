package Entidades;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Receta {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private List<Medicamento> medicamentos;
}
