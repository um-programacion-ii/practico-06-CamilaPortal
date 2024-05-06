package Implementaciones;

import DAO.EspecialidadDAO;
import Entidades.Especialidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspecialidadDAOimpl implements EspecialidadDAO {

    Map<Integer, Especialidad> especialidades = new HashMap();
    @Override
    public List<Especialidad> listarTodos() {
        return new ArrayList<>(especialidades.values());
    }

    @Override
    public Especialidad leerPorId(int id) {
        return especialidades.get(id);
    }

    @Override
    public void crear(Especialidad especialidad) {
        especialidades.put(especialidad.getId(), especialidad);
    }

    @Override
    public void actualizar(Especialidad especialidad) {
        if (especialidades.containsKey(especialidad.getId())) {
            especialidades.put(especialidad.getId(), especialidad);
        } else {
            throw new IllegalArgumentException("La especialidad con ID " + especialidad.getId() + " no existe en el DAO.");
        }
    }

    @Override
    public void eliminar(int id) {
        especialidades.remove(id);
    }
}
