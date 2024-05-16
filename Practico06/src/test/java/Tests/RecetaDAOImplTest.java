package Tests;
import DAO.RecetaDAO;
import Entidades.*;
import Implementaciones.RecetaDAOimpl;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RecetaDAOImplTest {
    private RecetaDAO recetaDAO;

    @BeforeEach
    public void setUp() {
        this.recetaDAO = new RecetaDAOimpl();
        Paciente paciente1 = new Paciente(1, "Pepe", "Honguito", null, true);
        Especialidad especialidad1 = new Especialidad(1, "Oncologia");
        Medico medico1 = new Medico(1, "Fernando", "House", especialidad1, null, true);
        Medicamento medicamento1 = new Medicamento(1, "Ibuprofeno", 2);
        Receta receta = new Receta(1, paciente1, medico1, Arrays.asList(medicamento1));
        this.recetaDAO.crear(receta);
    }

    @Test
    public void testListarTodos() {
        List<Receta> recetas = this.recetaDAO.listarTodos();
        Assertions.assertEquals(1, recetas.size());
    }

    @Test
    public void testLeerPorId() {
        Receta receta = (Receta)this.recetaDAO.leerPorId(1);
        Assertions.assertNotNull(receta);
        Assertions.assertEquals("Pepe", receta.getPaciente().getNombre());
    }

    @Test
    public void testActualizar() {
        Receta receta = (Receta)this.recetaDAO.leerPorId(1);
        receta.getPaciente().setNombre("Pepito");
        this.recetaDAO.actualizar(receta);
        Receta recetaActualizada = (Receta)this.recetaDAO.leerPorId(1);
        Assertions.assertEquals("Pepito", recetaActualizada.getPaciente().getNombre());
    }

    @Test
    public void testEliminar() {
        this.recetaDAO.eliminar(1);
        Assertions.assertNull(this.recetaDAO.leerPorId(1));
    }

    @Test
    public void testEditarCantidadMedicamentoEnReceta() {
        // Obtener la receta de la base de datos
        Receta receta = this.recetaDAO.leerPorId(1);
        int nuevaCantidad = 3;
        receta.getMedicamentos().get(0).setCantidad(nuevaCantidad);
        this.recetaDAO.actualizar(receta);
        Receta recetaActualizada = this.recetaDAO.leerPorId(1);
        Assertions.assertNotNull(recetaActualizada);
        Assertions.assertEquals(nuevaCantidad, recetaActualizada.getMedicamentos().get(0).getCantidad());
    }

    @Test
    public void testBuscarRecetasPorPaciente() {
        Paciente paciente = new Paciente(1, "Pepe", "Honguito", null, true);
        List<Receta> recetas = this.recetaDAO.buscarRecetasPorPaciente(paciente);
        Assertions.assertEquals(1, recetas.size());
    }

    @Test
    public void testBuscarRecetasPorMedico() {
        Medico medico = new Medico(1, "Fernando", "House", new Especialidad(1, "Oncologia"), null, true);
        List<Receta> recetas = this.recetaDAO.buscarRecetasPorMedico(medico);
        Assertions.assertEquals(1, recetas.size());
    }

    @Test
    public void testObtenerUltimaRecetaPaciente() {
        Paciente paciente = new Paciente(1, "Pepe", "Honguito", null, true);
        Receta receta = this.recetaDAO.obtenerUltimaRecetaPaciente(paciente);
        Assertions.assertNotNull(receta);
        Assertions.assertEquals(1, receta.getId());
    }

}
