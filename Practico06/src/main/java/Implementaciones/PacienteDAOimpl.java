package Implementaciones;

import DAO.PacienteDAO;
import Entidades.Paciente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacienteDAOimpl implements PacienteDAO {

    Map<Integer, Paciente> pacientes = new HashMap();

    @Override
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes.values());
    }

    @Override
    public Paciente leerPorId(int id) {
        return pacientes.get(id);
    }

    @Override
    public void crear(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    @Override
    public void actualizar(Paciente paciente) {
        if (pacientes.containsKey(paciente.getId())) {
            pacientes.put(paciente.getId(), paciente);
        } else {
            throw new IllegalArgumentException("El paciente con ID " + paciente.getId() + " no existe en el DAO.");
        }
    }

    @Override
    public void eliminar(int id) {
        pacientes.remove(id);
    }
}
