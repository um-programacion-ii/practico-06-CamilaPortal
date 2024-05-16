package Tests;
import DAO.PacienteDAO;
import Entidades.ObraSocial;
import Entidades.Paciente;
import Implementaciones.PacienteDAOimpl;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteDAOImplTest {
    private PacienteDAO pacienteDAO;

    @BeforeEach
    public void setUp() {
        this.pacienteDAO = new PacienteDAOimpl();
        ObraSocial osde = new ObraSocial(1, "OSDE");
        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", osde, false);
        this.pacienteDAO.crear(paciente1);
    }

    @Test
    public void testListarTodos() {
        List<Paciente> pacientes = this.pacienteDAO.listarTodos();
        Assertions.assertEquals(1, pacientes.size());
    }

    @Test
    public void testLeerPorId() {
        Paciente paciente = (Paciente)this.pacienteDAO.leerPorId(1);
        Assertions.assertNotNull(paciente);
        Assertions.assertEquals("Pepe", paciente.getNombre());
    }

    @Test
    public void testActualizar() {
        Paciente paciente = (Paciente)this.pacienteDAO.leerPorId(1);
        paciente.setNombre("Pepito");
        this.pacienteDAO.actualizar(paciente);
        Paciente pacienteActualizado = (Paciente)this.pacienteDAO.leerPorId(1);
        Assertions.assertEquals("Pepito", pacienteActualizado.getNombre());
    }

    @Test
    public void testEliminar() {
        this.pacienteDAO.eliminar(1);
        Assertions.assertNull(this.pacienteDAO.leerPorId(1));
    }
}