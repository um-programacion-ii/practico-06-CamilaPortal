package DAO;

import Entidades.Medico;
import Entidades.ObraSocial;
import Entidades.Paciente;
import Entidades.Turno;

import java.util.List;

public interface TurnoDAO extends DAO<Turno>{

    List<Turno> buscarTurnosPorPaciente(Paciente paciente);
    List<Turno> buscarTurnosPorMedico(Medico medico);
}
