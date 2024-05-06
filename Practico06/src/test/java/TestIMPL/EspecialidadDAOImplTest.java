package TestIMPL;
import DAO.EspecialidadDAO;
import Entidades.Especialidad;
import Implementaciones.EspecialidadDAOimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadDAOImplTest {
    private EspecialidadDAO especialidadDAO;

    @BeforeEach
    public void setUp() {
        this.especialidadDAO = new EspecialidadDAOimpl();
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        Especialidad pediatra = new Especialidad(2, "Pediatra");
        this.especialidadDAO.crear(cirujano);
        this.especialidadDAO.crear(pediatra);
    }

    @Test
    public void listarTodos() {
        Assertions.assertEquals(2, this.especialidadDAO.listarTodos().size());
    }

    @Test
    public void leerPorId() {
        Especialidad cirujano = (Especialidad)this.especialidadDAO.leerPorId(1);
        Assertions.assertEquals("Cirujano", cirujano.getNombre());
    }

    @Test
    public void crear() {
        Especialidad traumatologo = new Especialidad(3, "Traumatologo");
        this.especialidadDAO.crear(traumatologo);
        Assertions.assertEquals(3, this.especialidadDAO.listarTodos().size());
    }

    @Test
    public void actualizar() {
        Especialidad cirujano = (Especialidad)this.especialidadDAO.leerPorId(1);
        cirujano.setNombre("Cirujano General");
        this.especialidadDAO.actualizar(cirujano);
        Assertions.assertEquals("Cirujano General", ((Especialidad)this.especialidadDAO.leerPorId(1)).getNombre());
    }

    @Test
    public void eliminar() {
        this.especialidadDAO.eliminar(1);
        Assertions.assertEquals(1, this.especialidadDAO.listarTodos().size());
    }
}