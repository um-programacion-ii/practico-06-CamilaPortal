package Tests;

import Entidades.*;
import Servicios.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionTurnoServiceTest {

    private GestionTurnoService gestionTurnoService;
    private Contenedor contenedor;

    @BeforeEach
    public void setUp() {
        GestionTurnoService.reiniciarInstancia();
        Contenedor.reiniciarInstancia();
        contenedor = Contenedor.obtenerInstancia();
        gestionTurnoService = GestionTurnoService.obtenerInstancia(contenedor);
    }

    @Test
    public void testListarMedicosObraSocial() {

        ObraSocial osde = new ObraSocial(1, "OSDE");
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        Paciente paciente = new Paciente(1, "Pepe", "Honguito", osde, false);
        Medico medico1 = new Medico(1, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);
        Medico medico2 = new Medico(2, "María", "Fernandez", cirujano, Arrays.asList(osde), false);

        contenedor.getMedicoDAO().crear(medico1);
        contenedor.getMedicoDAO().crear(medico2);

        List <Medico> medicosDisponibles =gestionTurnoService.listarMedicos(paciente, cirujano);
        assertEquals(2, medicosDisponibles.size());
    }

    @Test
    public void testListarMedicosSinObraSocial() {

        ObraSocial osde = new ObraSocial(1, "OSDE");
        ObraSocial swissMedical = new ObraSocial(2, "SwissMedical");
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        Paciente paciente = new Paciente(1, "Pepe", "Honguito", null, false);
        Medico medico1 = new Medico(1, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);
        Medico medico2 = new Medico(2, "María", "Fernandez", cirujano, Arrays.asList(swissMedical), false);

        contenedor.getMedicoDAO().crear(medico1);
        contenedor.getMedicoDAO().crear(medico2);

        List <Medico> medicosDisponibles = gestionTurnoService.listarMedicos(paciente, cirujano);
        assertEquals(1, medicosDisponibles.size());
    }

    @Test
    public void testGenerarTurno() {

        ObraSocial osde = new ObraSocial(1, "OSDE");
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        Paciente paciente = new Paciente(1, "Pepe", "Honguito", osde, false);
        Medico medico1 = new Medico(1, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);

        contenedor.getMedicoDAO().crear(medico1);

        gestionTurnoService.generarTurno(paciente, medico1);
        assertEquals(1, contenedor.getTurnoDAO().listarTodos().size());
    }

    @Test
    public void testGenerarTurnoParticular() {
        ObraSocial osde = new ObraSocial(1, "OSDE");
        Especialidad cirujano = new Especialidad(1, "Cirujano");
        Paciente paciente = new Paciente(1, "Pepe", "Honguito", null, false);
        Medico medico1 = new Medico(1, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);

        contenedor.getMedicoDAO().crear(medico1);

        gestionTurnoService.generarTurno(paciente, medico1);
        assertEquals(1, contenedor.getTurnoDAO().listarTodos().size());
        assertTrue(contenedor.getTurnoDAO().listarTodos().get(0).isAtencionParticular());
    }
}
