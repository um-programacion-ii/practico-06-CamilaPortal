package TestIMPL;
import DAO.ObraSocialDAO;
import Entidades.ObraSocial;
import Implementaciones.ObraSocialDAOimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObraSocialDAOImplTest {
    private ObraSocialDAO obraSocialDAO;

    public ObraSocialDAOImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.obraSocialDAO = new ObraSocialDAOimpl();
        ObraSocial osde = new ObraSocial(1, "OSDE");
        ObraSocial swissMedical = new ObraSocial(2, "SwissMedical");
        this.obraSocialDAO.crear(osde);
        this.obraSocialDAO.crear(swissMedical);
    }

    @Test
    public void testListarTodos() {
        Assertions.assertEquals(2, this.obraSocialDAO.listarTodos().size());
    }

    @Test
    public void testLeerPorId() {
        ObraSocial obraSocial = (ObraSocial)this.obraSocialDAO.leerPorId(1);
        Assertions.assertNotNull(obraSocial);
        Assertions.assertEquals("OSDE", obraSocial.getNombre());
    }

    @Test
    public void testActualizar() {
        ObraSocial obraSocial = (ObraSocial)this.obraSocialDAO.leerPorId(1);
        obraSocial.setNombre("Galeno");
        this.obraSocialDAO.actualizar(obraSocial);
        ObraSocial obraSocialActualizada = (ObraSocial)this.obraSocialDAO.leerPorId(1);
        Assertions.assertEquals("Galeno", obraSocialActualizada.getNombre());
    }

    @Test
    public void testEliminar() {
        this.obraSocialDAO.eliminar(1);
        Assertions.assertNull(this.obraSocialDAO.leerPorId(1));
    }

    @Test
    public void testActualizarInexistente() {
        ObraSocial obraSocial = new ObraSocial(3, "OSDE 310");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.obraSocialDAO.actualizar(obraSocial);
        });
    }

    @Test
    public void testLeerPorIdInexistente() {
        Assertions.assertNull(this.obraSocialDAO.leerPorId(3));
    }
}
