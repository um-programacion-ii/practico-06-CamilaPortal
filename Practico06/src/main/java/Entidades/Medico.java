package Entidades;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medico {
    private int id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;
    private List<ObraSocial> obrasSociales;
    private boolean particular;
}
