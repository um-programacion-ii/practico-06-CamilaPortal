package Implementaciones;

import DAO.ObraSocialDAO;
import Entidades.ObraSocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObraSocialDAOimpl implements ObraSocialDAO {

    Map<Integer, ObraSocial> obrasSociales = new HashMap();

    @Override
    public List<ObraSocial> listarTodos() {
        return new ArrayList<>(obrasSociales.values());
    }

    @Override
    public ObraSocial leerPorId(int id) {
        return obrasSociales.get(id);
    }

    @Override
    public void crear(ObraSocial obraSocial) {
        obrasSociales.put(obraSocial.getId(), obraSocial);
    }

    @Override
    public void actualizar(ObraSocial obraSocial) {
        if (obrasSociales.containsKey(obraSocial.getId())) {
            obrasSociales.put(obraSocial.getId(), obraSocial);
        } else {
            throw new IllegalArgumentException("La obra social con ID " + obraSocial.getId() + " no existe en el DAO.");
        }
    }

    @Override
    public void eliminar(int id) {
        obrasSociales.remove(id);
    }
}
