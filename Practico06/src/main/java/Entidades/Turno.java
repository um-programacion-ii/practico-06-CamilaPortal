package Entidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Turno {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private boolean estado;
    private boolean atencionParticular;
    private ObraSocial obraSocial;
}
