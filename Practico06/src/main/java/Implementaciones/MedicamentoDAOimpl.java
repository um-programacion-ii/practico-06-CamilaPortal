package Implementaciones;

import DAO.MedicamentoDAO;
import Entidades.Medicamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicamentoDAOimpl implements MedicamentoDAO {
    Map<Integer, Medicamento> medicamentos = new HashMap();
    @Override
    public List<Entidades.Medicamento> listarTodos() {
        return new ArrayList<>(medicamentos.values());
    }

    @Override
    public Entidades.Medicamento leerPorId(int id) {
        return medicamentos.get(id);
    }

    @Override
    public void crear(Entidades.Medicamento medicamento) {
        medicamentos.put(medicamento.getId(), medicamento);
    }

    @Override
    public void actualizar(Entidades.Medicamento medicamento) {
        if (medicamentos.containsKey(medicamento.getId())) {
            medicamentos.put(medicamento.getId(), medicamento);
        } else {
            throw new IllegalArgumentException("El medicamento con ID " + medicamento.getId() + " no existe en el DAO.");
        }
    }

    @Override
    public void eliminar(int id) {
        medicamentos.remove(id);
    }
}
