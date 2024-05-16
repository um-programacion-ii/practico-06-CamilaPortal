package Tests;

import Entidades.*;
import Servicios.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicaAppTest {

//    @BeforeEach
//    public void setUp() {
//
//    }

    @Test
    public void testIniciarClinica() {
        Contenedor.reiniciarInstancia();
        GestionTurnoService.reiniciarInstancia();
        Contenedor contenedor = Contenedor.obtenerInstancia();
        contenedor.getTurnoDAO().limpiarTurnos();
        GestionTurnoService gestionTurnoService = GestionTurnoService.obtenerInstancia(contenedor);
        Farmacia farmacia = new Farmacia(new ArrayList<>());
        Drogueria drogueria = new Drogueria();
        ClinicaApp clinicaApp = new ClinicaApp();

        Medicamento medicamento1 = new Medicamento(0, "Ibuprofeno", 2);
        Medicamento medicamento2 = new Medicamento(1, "Paracetamol", 3);
        contenedor.getMedicamentoDAO().crear(medicamento1);
        contenedor.getMedicamentoDAO().crear(medicamento2);
        farmacia.agregarMedicamento(medicamento1);
        farmacia.agregarMedicamento(medicamento2);

        Especialidad cirujano = new Especialidad(0, "Cirujano");
        Especialidad pediatra = new Especialidad(1, "Pediatra");
        contenedor.getEspecialidadDAO().crear(cirujano);
        contenedor.getEspecialidadDAO().crear(pediatra);

        ObraSocial osde = new ObraSocial(0, "OSDE");
        ObraSocial swissMedical = new ObraSocial(1, "SwissMedical");
        contenedor.getObraSocialDAO().crear(osde);
        contenedor.getObraSocialDAO().crear(swissMedical);

        Medico medico1 = new Medico(0, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);
        Medico medico2 = new Medico(1, "María", "Fernandez", pediatra, Arrays.asList(osde), false);
        Medico medico3 = new Medico(2, "Juan", "Perez", cirujano, Arrays.asList(osde), true);
        Medico medico4 = new Medico(3, "Ana", "Rodriguez", pediatra, Arrays.asList(osde), false);
        Medico medico5 = new Medico(4, "Pedro", "Gonzalez", cirujano, Arrays.asList(swissMedical), true);
        Medico medico6 = new Medico(5, "Laura", "Garcia", pediatra, Arrays.asList(swissMedical), false);
        Medico medico7 = new Medico(6, "Lucas", "Fernandez", cirujano, Arrays.asList(swissMedical), true);
        Medico medico8 = new Medico(7, "Sofia", "Rodriguez", pediatra, Arrays.asList(swissMedical), false);
        contenedor.getMedicoDAO().crear(medico1);
        contenedor.getMedicoDAO().crear(medico2);
        contenedor.getMedicoDAO().crear(medico3);
        contenedor.getMedicoDAO().crear(medico4);
        contenedor.getMedicoDAO().crear(medico5);
        contenedor.getMedicoDAO().crear(medico6);
        contenedor.getMedicoDAO().crear(medico7);
        contenedor.getMedicoDAO().crear(medico8);

        Paciente paciente1 = new Paciente(0, "Pepe", "Honguito", osde, false);
        contenedor.getPacienteDAO().crear(paciente1);

        clinicaApp.iniciarClinica(farmacia, drogueria, cirujano, paciente1);

        assertEquals(2, farmacia.getStock().size());

        assertFalse(paciente1.isTieneReceta());


        List<Medico> medicosDisponibles = gestionTurnoService.listarMedicos(paciente1, cirujano);
        assertEquals(2, medicosDisponibles.size());
    }

//    @Test
//    public void testIniciarClinica_pacienteSinOS() {
//        Contenedor contenedor = Contenedor.obtenerInstancia();
//        GestionTurnoService gestionTurnoService = GestionTurnoService.obtenerInstancia(contenedor);
//        Farmacia farmacia = new Farmacia(new ArrayList<>());
//        Drogueria drogueria = new Drogueria();
//        ClinicaApp clinicaApp = new ClinicaApp();
//
//        Medicamento medicamento1 = new Medicamento(0, "Ibuprofeno", 2);
//        Medicamento medicamento2 = new Medicamento(1, "Paracetamol", 3);
//        contenedor.getMedicamentoDAO().crear(medicamento1);
//        contenedor.getMedicamentoDAO().crear(medicamento2);
//        farmacia.agregarMedicamento(medicamento1);
//        farmacia.agregarMedicamento(medicamento2);
//
//        Especialidad cirujano = new Especialidad(0, "Cirujano");
//        Especialidad pediatra = new Especialidad(1, "Pediatra");
//        contenedor.getEspecialidadDAO().crear(cirujano);
//        contenedor.getEspecialidadDAO().crear(pediatra);
//
//        ObraSocial osde = new ObraSocial(0, "OSDE");
//        ObraSocial swissMedical = new ObraSocial(1, "SwissMedical");
//        contenedor.getObraSocialDAO().crear(osde);
//        contenedor.getObraSocialDAO().crear(swissMedical);
//
//        Medico medico1 = new Medico(0, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);
//        Medico medico2 = new Medico(1, "María", "Fernandez", pediatra, Arrays.asList(osde), false);
//        Medico medico3 = new Medico(2, "Juan", "Perez", cirujano, Arrays.asList(osde), true);
//        Medico medico4 = new Medico(3, "Ana", "Rodriguez", pediatra, Arrays.asList(osde), false);
//        Medico medico5 = new Medico(4, "Pedro", "Gonzalez", cirujano, Arrays.asList(swissMedical), true);
//        Medico medico6 = new Medico(5, "Laura", "Garcia", pediatra, Arrays.asList(swissMedical), false);
//        Medico medico7 = new Medico(6, "Lucas", "Fernandez", cirujano, Arrays.asList(swissMedical), true);
//        Medico medico8 = new Medico(7, "Sofia", "Rodriguez", pediatra, Arrays.asList(swissMedical), true);
//        contenedor.getMedicoDAO().crear(medico1);
//        contenedor.getMedicoDAO().crear(medico2);
//        contenedor.getMedicoDAO().crear(medico3);
//        contenedor.getMedicoDAO().crear(medico4);
//        contenedor.getMedicoDAO().crear(medico5);
//        contenedor.getMedicoDAO().crear(medico6);
//        contenedor.getMedicoDAO().crear(medico7);
//        contenedor.getMedicoDAO().crear(medico8);
//
//        Paciente paciente1 = new Paciente(0, "Pepe", "Honguito", null, false);
//        contenedor.getPacienteDAO().crear(paciente1);
//
//        clinicaApp.iniciarClinica(farmacia, drogueria, pediatra, paciente1);
//
//        assertEquals(2, farmacia.getStock().size());
//
//        assertFalse(paciente1.isTieneReceta());
//
//
//        List<Medico> medicosDisponibles = gestionTurnoService.listarMedicos(paciente1, pediatra);
//        assertEquals(1, medicosDisponibles.size());
//    }

    @Test
    public void testAtencionMedicaSinMedicosDisponibles(){
        Contenedor.reiniciarInstancia();
        GestionTurnoService.reiniciarInstancia();
        Contenedor contenedor = Contenedor.obtenerInstancia();
        GestionTurnoService gestionTurnoService = GestionTurnoService.obtenerInstancia(contenedor);
        contenedor.getTurnoDAO().limpiarTurnos();
        Farmacia farmacia = new Farmacia(new ArrayList<>());
        Drogueria drogueria = new Drogueria();
        ClinicaApp clinicaApp = new ClinicaApp();

        ObraSocial osde = new ObraSocial(0, "OSDE");
        ObraSocial swissMedical = new ObraSocial(1, "SwissMedical");
        contenedor.getObraSocialDAO().crear(osde);
        contenedor.getObraSocialDAO().crear(swissMedical);

        Especialidad cirujano = new Especialidad(0, "Cirujano");
        Especialidad pediatra = new Especialidad(1, "Pediatra");
        contenedor.getEspecialidadDAO().crear(cirujano);
        contenedor.getEspecialidadDAO().crear(pediatra);

        Paciente paciente1 = new Paciente(0, "Pepe", "Honguito", osde, false);
        contenedor.getPacienteDAO().crear(paciente1);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        clinicaApp.iniciarClinica(farmacia, drogueria, cirujano, paciente1);

        assertTrue(outContent.toString().contains("No hay médicos disponibles para la especialidad seleccionada."));

        assertFalse(paciente1.isTieneReceta());
        List<Medico> medicosDisponibles = gestionTurnoService.listarMedicos(paciente1, cirujano);
        assertEquals(0, medicosDisponibles.size());
    }


}
