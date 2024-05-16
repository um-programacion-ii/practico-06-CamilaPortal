package Implementaciones;

import DAO.RecetaDAO;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Receta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecetaDAOimpl implements RecetaDAO {
    Map<Integer, Receta> recetas = new HashMap();

    @Override
    public List<Receta> listarTodos() {
        return new ArrayList<>(recetas.values());
    }

    @Override
    public Receta leerPorId(int id) {
        return recetas.get(id);
    }

    @Override
    public void crear(Receta receta) {
        recetas.put(receta.getId(), receta);

    }

    @Override
    public void actualizar(Receta receta) {
        if (recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
        } else {
            throw new IllegalArgumentException("La receta con ID " + receta.getId() + " no existe en el DAO.");
        }

    }

    @Override
    public void eliminar(int id) {
        recetas.remove(id);
    }

    @Override
    public List<Receta> buscarRecetasPorPaciente(Paciente paciente) {
        List<Receta> recetasPorPaciente = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getPaciente().equals(paciente)) {
                recetasPorPaciente.add(receta);
            }
        }
        return recetasPorPaciente;
    }

    @Override
    public List<Receta> buscarRecetasPorMedico(Medico medico) {
        List<Receta> recetasPorMedico = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getMedico().equals(medico)) {
                recetasPorMedico.add(receta);
            }
        }
        return recetasPorMedico;
    }

    @Override
    public Receta obtenerUltimaRecetaPaciente(Paciente paciente) {
        List<Receta> recetas = buscarRecetasPorPaciente(paciente);
        if (!recetas.isEmpty()) {
            return recetas.getLast();
        } else {
            return null;
        }
    }
}
