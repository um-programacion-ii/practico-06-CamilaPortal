package Entidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Turno {
    private static int contadorIds = 0;

    private int id;
    private Paciente paciente;
    private Medico medico;
    private boolean estado;
    private boolean atencionParticular;
    private ObraSocial obraSocial;

    public Turno(Paciente paciente, Medico medico, boolean atencionParticular, boolean estado, ObraSocial obraSocial) {
        this.id = contadorIds++;
        this.paciente = paciente;
        this.medico = medico;
        this.estado = estado;
        this.atencionParticular = atencionParticular;
        this.obraSocial = obraSocial;
    }
}
