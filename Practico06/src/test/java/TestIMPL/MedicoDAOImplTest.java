package TestIMPL;
import DAO.MedicoDAO;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.ObraSocial;
import Implementaciones.MedicoDAOimpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicoDAOImplTest {
    private MedicoDAO medicoDAO;

    @BeforeEach
    public void setUp() {
        this.medicoDAO = new MedicoDAOimpl();
        ObraSocial osde = new ObraSocial(1, "OSDE");
        ObraSocial swissMedical = new ObraSocial(2, "SwissMedical");
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        Especialidad pediatra = new Especialidad(2, "Pediatra");
        Medico medico1 = new Medico(1, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);
        Medico medico2 = new Medico(2, "María", "Fernandez", pediatra, Arrays.asList(osde, swissMedical), false);
        this.medicoDAO.crear(medico1);
        this.medicoDAO.crear(medico2);
    }

    @Test
    public void testListarTodos() {
        List<Medico> medicos = this.medicoDAO.listarTodos();
        Assertions.assertEquals(2, medicos.size());
    }

    @Test
    public void testLeerPorId() {
        Medico medico = (Medico)this.medicoDAO.leerPorId(1);
        Assertions.assertNotNull(medico);
        Assertions.assertEquals("Carlos", medico.getNombre());
    }

    @Test
    public void testActualizar() {
        Medico medico = (Medico)this.medicoDAO.leerPorId(1);
        medico.setNombre("Pedro");
        this.medicoDAO.actualizar(medico);
        Medico medicoActualizado = (Medico)this.medicoDAO.leerPorId(1);
        Assertions.assertEquals("Pedro", medicoActualizado.getNombre());
    }

    @Test
    public void testEliminar() {
        this.medicoDAO.eliminar(1);
        Assertions.assertNull(this.medicoDAO.leerPorId(1));
    }

    @Test
    public void testListarPorEspecialidad() {
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        List<Medico> medicos = this.medicoDAO.listarPorEspecialidad(cirujano);
        Assertions.assertEquals(1, medicos.size());
    }

    @Test
    public void testListarPorObraSocial() {
        ObraSocial osde = new ObraSocial(1, "OSDE");
        List<Medico> medicos = this.medicoDAO.listarPorObraSocial(osde);
        Assertions.assertEquals(2, medicos.size());
    }

}
