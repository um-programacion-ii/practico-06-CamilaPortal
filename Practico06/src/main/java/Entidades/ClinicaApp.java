package Entidades;

import Servicios.AtencionMedicoService;
import Servicios.GestionFarmaciaService;
import Servicios.GestionTurnoService;

import java.util.List;
import java.util.Random;

public class ClinicaApp {

    public void iniciarClinica(Farmacia farmacia, Drogueria drogueria, Especialidad especialidad, Paciente paciente){

        Contenedor contenedor = Contenedor.obtenerInstancia();
        GestionTurnoService gestionTurnoService = GestionTurnoService.obtenerInstancia(contenedor);
        AtencionMedicoService atencionMedicoService = AtencionMedicoService.obtenerInstancia(contenedor);
        GestionFarmaciaService gestionFarmaciaService = GestionFarmaciaService.obtenerInstancia(farmacia, drogueria);

        while (true) {
            List<Medico> medicosDisponibles = gestionTurnoService.listarMedicos(paciente, especialidad);

            if (medicosDisponibles.isEmpty()) {
                System.out.println("No hay médicos disponibles para la especialidad seleccionada.");
                break;
            }

            Medico medicoSeleccionado = medicosDisponibles.get(new Random().nextInt(medicosDisponibles.size()));

            gestionTurnoService.generarTurno(paciente, medicoSeleccionado);

            Turno turno = contenedor.getTurnoDAO().obtenerUltimoTurno();

            atencionMedicoService.comenzarAtencion(turno);

            atencionMedicoService.crearReceta(paciente, medicoSeleccionado);

            if (paciente.isTieneReceta()) {
                Receta receta = contenedor.getRecetaDAO().obtenerUltimaRecetaPaciente(paciente);

                if (receta != null) {
                    gestionFarmaciaService.procesarReceta(receta);
                    paciente.setTieneReceta(false);
                    contenedor.getPacienteDAO().actualizar(paciente);
                    break;
                }
            } else {
                atencionMedicoService.finalizarAtencion(turno);
                break;
            }

        }
    }
}
