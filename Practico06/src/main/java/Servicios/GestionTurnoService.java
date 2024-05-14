package Servicios;

import DAO.EspecialidadDAO;
import Entidades.*;
import Implementaciones.EspecialidadDAOimpl;
import Implementaciones.MedicoDAOimpl;
import Implementaciones.PacienteDAOimpl;
import Implementaciones.TurnoDAOimpl;

import java.util.List;

public class GestionTurnoService {
    private static GestionTurnoService instancia;
    private Contenedor contenedor;

    public static void reiniciarInstancia() {
        instancia = null;
    }

    private GestionTurnoService(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public static synchronized GestionTurnoService obtenerInstancia(Contenedor contenedor) {
        if (instancia == null) {
            instancia = new GestionTurnoService(contenedor);
        }
        return instancia;
    }

    public void listarMedicos(Paciente paciente, Especialidad especialidad) {
        List<Medico> medicosDisponibles;
        if (paciente.getObraSocial() != null) {
            medicosDisponibles = contenedor.getMedicoDAO().listarPorEspecialidadConObraSocial(especialidad, paciente.getObraSocial());
        } else {
            medicosDisponibles = contenedor.getMedicoDAO().listarPorEspecialidadYParticular(especialidad);
        }
    }

    public void generarTurno(Paciente paciente, Medico medico) {
        boolean atencionParticular = paciente.getObraSocial() == null;
        Turno nuevoTurno = new Turno(paciente, medico,atencionParticular, false, paciente.getObraSocial());
        contenedor.getTurnoDAO().crear(nuevoTurno);
    }

}
