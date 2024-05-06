package Implementaciones;

import DAO.MedicoDAO;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.ObraSocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicoDAOimpl implements MedicoDAO {

    Map<Integer, Medico> medicos = new HashMap();

    @Override
    public List<Medico> listarTodos() {
        return new ArrayList<>(medicos.values());
    }

    @Override
    public Medico leerPorId(int id) {
        return medicos.get(id);
    }

    @Override
    public void crear(Medico medico) {
        medicos.put(medico.getId(), medico);
    }

    @Override
    public void actualizar(Medico medico) {
        if (medicos.containsKey(medico.getId())) {
            medicos.put(medico.getId(), medico);
        } else {
            throw new IllegalArgumentException("El médico con ID " + medico.getId() + " no existe en el DAO.");
        }
    }

    @Override
    public void eliminar(int id) {
        medicos.remove(id);
    }

    @Override
    public List<Medico> listarPorEspecialidad(Especialidad especialidad) {
        List<Medico> medicosPorEspecialidad = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            if (medico.getEspecialidad().equals(especialidad)) {
                medicosPorEspecialidad.add(medico);
            }
        }
        return medicosPorEspecialidad;
    }

    @Override
    public List<Medico> listarPorObraSocial(ObraSocial obraSocial) {
        List<Medico> medicosPorObraSocial = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            if (medico.getObrasSociales().contains(obraSocial)) {
                medicosPorObraSocial.add(medico);
            }
        }
        return medicosPorObraSocial;
    }

    @Override
    public List<Medico> listarSiAtiendeParticular() {
        List<Medico> medicosAtiendenParticular = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            if (medico.isParticular()) {
                medicosAtiendenParticular.add(medico);
            }
        }
        return medicosAtiendenParticular;
    }
}
