package Tests;

import DAO.MedicamentoDAO;
import Entidades.Medicamento;
import Implementaciones.MedicamentoDAOimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicamentoDAOImplTest {
    private MedicamentoDAO medicamentoDAO;

    @BeforeEach
    public void setUp() {
        this.medicamentoDAO = new MedicamentoDAOimpl();
        Medicamento paracetamol = new Medicamento(1, "Paracetamol", 10);
        Medicamento ibuprofeno = new Medicamento(2, "Ibuprofeno", 20);
        this.medicamentoDAO.crear(paracetamol);
        this.medicamentoDAO.crear(ibuprofeno);
    }

    @Test
    public void listarTodos() {
        Assertions.assertEquals(2, this.medicamentoDAO.listarTodos().size());
    }

    @Test
    public void leerPorId() {
        Medicamento paracetamol = (Medicamento)this.medicamentoDAO.leerPorId(1);
        Assertions.assertEquals("Paracetamol", paracetamol.getNombre());
    }

    @Test
    public void crear() {
        Medicamento aspirina = new Medicamento(3, "Aspirina", 30);
        this.medicamentoDAO.crear(aspirina);
        Assertions.assertEquals(3, this.medicamentoDAO.listarTodos().size());
    }

    @Test
    public void actualizar() {
        Medicamento paracetamol = (Medicamento)this.medicamentoDAO.leerPorId(1);
        paracetamol.setNombre("Paracetamol 500mg");
        this.medicamentoDAO.actualizar(paracetamol);
        Assertions.assertEquals("Paracetamol 500mg", ((Medicamento)this.medicamentoDAO.leerPorId(1)).getNombre());
    }

    @Test
    public void eliminar() {
        this.medicamentoDAO.eliminar(1);
        Assertions.assertEquals(1, this.medicamentoDAO.listarTodos().size());
    }

}
