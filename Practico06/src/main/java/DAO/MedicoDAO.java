package DAO;

import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.ObraSocial;

import java.util.List;

public interface MedicoDAO extends DAO<Medico>{
    List<Medico> listarPorEspecialidad(Especialidad especialidad);

    List<Medico> listarPorObraSocial(ObraSocial obraSocial);

    List<Medico> listarSiAtiendeParticular();
}
