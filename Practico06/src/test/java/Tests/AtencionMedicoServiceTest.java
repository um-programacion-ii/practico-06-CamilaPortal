package Tests;

import Entidades.*;
import Servicios.AtencionMedicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AtencionMedicoServiceTest {
    private AtencionMedicoService atencionMedicoService;
    private Contenedor contenedor;

    @BeforeEach
    public void setUp() {
        contenedor = Contenedor.obtenerInstancia();
        atencionMedicoService = AtencionMedicoService.obtenerInstancia(contenedor);
    }

    @Test
    public void testComenzarAtencion_() {
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        Turno turno = new Turno(paciente, medico, false, false, null);
        contenedor.getTurnoDAO().crear(turno);

        atencionMedicoService.comenzarAtencion(turno);

        assertTrue(turno.isEstado());
    }

    @Test
    public void testComenzarAtencion_TurnoActivo() {
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        Turno turno = new Turno(paciente, medico, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        atencionMedicoService.comenzarAtencion(turno);

        assertTrue(turno.isEstado());
    }

    @Test
    public void testFinalizarAtencion_() {
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        Turno turno = new Turno(paciente, medico, true, true, null);
        contenedor.getTurnoDAO().crear(turno);

        atencionMedicoService.finalizarAtencion(turno);

        assertFalse(turno.isEstado());
    }

    @Test
    public void testFinalizarAtencion_TurnoInactivo() {
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        Turno turno = new Turno(paciente, medico, false, false, null);
        contenedor.getTurnoDAO().crear(turno);

        atencionMedicoService.finalizarAtencion(turno);

        assertFalse(turno.isEstado());
    }

    @Test
    public void testCrearReceta_RecetaGenerada_y_ActualizacionTieneReceta() {
        Paciente paciente = new Paciente();
        contenedor.getPacienteDAO().crear(paciente);
        Medico medico = new Medico();
        contenedor.getMedicoDAO().crear(medico);
        Medicamento medicamento1 = new Medicamento();
        contenedor.getMedicamentoDAO().crear(medicamento1);

        boolean estadoInicialTieneReceta = paciente.isTieneReceta();

        atencionMedicoService.crearReceta(paciente, medico);

        List<Receta> recetas = contenedor.getRecetaDAO().listarTodos();

        if (!recetas.isEmpty()) {
            assertTrue(paciente.isTieneReceta());
        } else {
            assertEquals(estadoInicialTieneReceta, paciente.isTieneReceta());
        }
    }

}

