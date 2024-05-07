package TestIMPL;

import DAO.TurnoDAO;
import Entidades.Paciente;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.Turno;
import Implementaciones.TurnoDAOimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TurnoDAOImpl {
    private TurnoDAO turnoDAO;

    @BeforeEach
    public void setUp() {
        this.turnoDAO = new TurnoDAOimpl();
        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Turno turno = new Turno(1, paciente1, medico1, true, true, null);
        this.turnoDAO.crear(turno);
    }

    @Test
    public void testListarTodos() {
        List<Turno> turnos = this.turnoDAO.listarTodos();
        Assertions.assertEquals(1, turnos.size());
    }

    @Test
    public void testLeerPorId() {
        Turno turno = (Turno)this.turnoDAO.leerPorId(1);
        Assertions.assertNotNull(turno);
        Assertions.assertEquals("Pepe", turno.getPaciente().getNombre());
    }

    @Test
    public void testActualizar() {
        Turno turno = (Turno)this.turnoDAO.leerPorId(1);
        turno.getPaciente().setNombre("Pepito");
        this.turnoDAO.actualizar(turno);
        Turno turnoActualizado = (Turno)this.turnoDAO.leerPorId(1);
        Assertions.assertEquals("Pepito", turnoActualizado.getPaciente().getNombre());
    }

    @Test
    public void testEliminar() {
        this.turnoDAO.eliminar(1);
        Assertions.assertNull(this.turnoDAO.leerPorId(1));
    }

}
