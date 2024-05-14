package Entidades;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Receta {
    private static int contadorIds = 0;

    private int id;
    private Paciente paciente;
    private Medico medico;
    private List<Medicamento> medicamentos;

    public Receta(Paciente paciente, Medico medico, List<Medicamento> medicamentos) {
        this.id = contadorIds++;
        this.paciente = paciente;
        this.medico = medico;
        this.medicamentos = medicamentos;
    }
}
