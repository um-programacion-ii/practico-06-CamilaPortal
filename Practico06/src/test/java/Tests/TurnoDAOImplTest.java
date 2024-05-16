package Tests;

import DAO.TurnoDAO;
import Entidades.*;
import Implementaciones.TurnoDAOimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TurnoDAOImplTest {
    private TurnoDAO turnoDAO;


    @Test
    public void testListarTodos() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        List<Turno> turnos = contenedor.getTurnoDAO().listarTodos();
        Assertions.assertEquals(1, turnos.size());
    }

    @Test
    public void testLeerPorId() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        Turno turnoObtenido = contenedor.getTurnoDAO().leerPorId(1);
        Assertions.assertNotNull(turnoObtenido);
        Assertions.assertEquals("Pepe", turnoObtenido.getPaciente().getNombre());
    }

    @Test
    public void testActualizar() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        Turno turnoObtenido = contenedor.getTurnoDAO().leerPorId(1);
        turnoObtenido.getPaciente().setNombre("Pepito");
        contenedor.getTurnoDAO().actualizar(turno);
        Turno turnoActualizado = contenedor.getTurnoDAO().leerPorId(1);
        Assertions.assertEquals("Pepito", turnoActualizado.getPaciente().getNombre());
    }

    @Test
    public void testEliminar() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1,paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        contenedor.getTurnoDAO().eliminar(1);
        Assertions.assertNull(contenedor.getTurnoDAO().leerPorId(1));
    }

    @Test
    public void buscarTurnoPorMedico() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

//        Medico medico = new Medico(1, "Fernando", "House", new Especialidad(1, "Oncologia"), null, true);
        List<Turno> turnos = contenedor.getTurnoDAO().buscarTurnosPorMedico(medico1);
        Assertions.assertEquals(1, turnos.size());
    }

    @Test
    public void buscarTurnoPorPaciente() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

//        Paciente paciente = new Paciente(1, "Pepe", "Honguito", null, true);
        List<Turno> turnos = contenedor.getTurnoDAO().buscarTurnosPorPaciente(paciente1);
        Assertions.assertEquals(1, turnos.size());
    }

    @Test
    public void crear() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno1 = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno1);

        Paciente paciente = new Paciente(2, "Juan", "Perez", null, true);
        Especialidad especialidad = new Especialidad(2, "Cardiologia");
        Medico medico = new Medico(2, "Juan", "Perez", especialidad, null, true);
        Turno turno2 = new Turno(2, paciente, medico, true, true, null);
        contenedor.getTurnoDAO().crear(turno2);

        Assertions.assertEquals(2, contenedor.getTurnoDAO().listarTodos().size());
    }

    @Test
    public void obtenerUltimoTurno() {
        Contenedor.reiniciarInstancia();
        Contenedor contenedor=Contenedor.obtenerInstancia();

        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        Turno turnoUltimo = contenedor.getTurnoDAO().obtenerUltimoTurno();
        Assertions.assertNotNull(turnoUltimo);
        Assertions.assertEquals(1, turnoUltimo.getId());
    }
}
