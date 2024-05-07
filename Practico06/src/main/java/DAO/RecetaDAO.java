package DAO;

import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Receta;

import java.util.List;

public interface RecetaDAO extends DAO<Receta>{
    List<Receta> buscarRecetasPorPaciente(Paciente paciente);
    List<Receta> buscarRecetasPorMedico(Medico medico);
}
