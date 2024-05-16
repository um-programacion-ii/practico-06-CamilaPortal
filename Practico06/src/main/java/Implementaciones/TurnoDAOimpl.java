package Implementaciones;

import DAO.TurnoDAO;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Turno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnoDAOimpl implements TurnoDAO {

    Map<Integer, Turno> turnos = new HashMap();

    @Override
    public List<Turno> listarTodos() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public Turno leerPorId(int id) {
        return turnos.get(id);
    }

    @Override
    public void crear(Turno turno) {
        turnos.put(turno.getId(), turno);
    }

    @Override
    public void actualizar(Turno turno) {
        if (turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
        } else {
            throw new IllegalArgumentException("El turno con ID " + turno.getId() + " no existe en el DAO.");
        }
    }

    @Override
    public void eliminar(int id) {
        turnos.remove(id);
    }

    @Override
    public List<Turno> buscarTurnosPorPaciente(Paciente paciente) {
        List<Turno> turnosPorPaciente = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getPaciente().equals(paciente)) {
                turnosPorPaciente.add(turno);
            }
        }
        return turnosPorPaciente;
    }

    @Override
    public List<Turno> buscarTurnosPorMedico(Medico medico) {
        List<Turno> turnosPorMedico = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getMedico().equals(medico)) {
                turnosPorMedico.add(turno);
            }
        }
        return turnosPorMedico;
    }

    @Override
    public Turno obtenerUltimoTurno() {
        Turno ultimoTurno = null;
        for (Turno turno : turnos.values()) {
            if (ultimoTurno == null || turno.getId() > ultimoTurno.getId()) {
                ultimoTurno = turno;
            }
        }
        return ultimoTurno;
    }

    @Override
    public void limpiarTurnos() {
        turnos.clear();
    }
}
