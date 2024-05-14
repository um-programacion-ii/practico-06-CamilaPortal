package Servicios;

import Entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AtencionMedicoService {
    private static AtencionMedicoService instancia;
    private Contenedor contenedor;

    public static void reiniciarInstancia() {
        instancia = null;
    }

    private AtencionMedicoService(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public static synchronized AtencionMedicoService obtenerInstancia(Contenedor contenedor) {
        if (instancia == null) {
            instancia = new AtencionMedicoService(contenedor);
        }
        return instancia;
    }

    public void comenzarAtencion(Turno turno) {
        turno.setEstado(true);
        contenedor.getTurnoDAO().actualizar(turno);
    }

    public void crearReceta(Paciente paciente, Medico medico) {
        double probabilidadReceta = 0.7;

        Random random = new Random();
        if (random.nextDouble() < probabilidadReceta) {
            List<Medicamento> todosMedicamentos = contenedor.getMedicamentoDAO().listarTodos();

            List<Medicamento> medicamentosReceta = generarMedicamentosAleatorios(todosMedicamentos);

            Receta receta = new Receta(paciente, medico, medicamentosReceta);
            contenedor.getRecetaDAO().crear(receta);
            paciente.setTieneReceta(true);
            contenedor.getPacienteDAO().actualizar(paciente);
        } else {
            paciente.setTieneReceta(false);
            contenedor.getPacienteDAO().actualizar(paciente);
        }
    }

    private List<Medicamento> generarMedicamentosAleatorios(List<Medicamento> todosMedicamentos) {
        List<Medicamento> medicamentosReceta = new ArrayList<>();
        Random random = new Random();
        int cantidadMedicamentos = random.nextInt(4) + 1;

        // Seleccionar medicamentos aleatorios de la lista
        for (int i = 0; i < cantidadMedicamentos; i++) {
            int indiceMedicamento = random.nextInt(todosMedicamentos.size());
            medicamentosReceta.add(todosMedicamentos.get(indiceMedicamento));
        }

        return medicamentosReceta;
    }

    public void finalizarAtencion(Turno turno) {
        turno.setEstado(false);
        contenedor.getTurnoDAO().actualizar(turno);
    }
}
