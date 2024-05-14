package Entidades;

import DAO.*;
import Implementaciones.*;
import lombok.Getter;

public class Contenedor {
    public  static Contenedor instancia;
    @Getter
    private MedicoDAO medicoDAO= new MedicoDAOimpl();
    @Getter
    private PacienteDAO pacienteDAO= new PacienteDAOimpl();
    @Getter
    private RecetaDAO recetaDAO= new RecetaDAOimpl();
    @Getter
    private TurnoDAO turnoDAO= new TurnoDAOimpl();
    @Getter
    private EspecialidadDAO especialidadDAO= new EspecialidadDAOimpl();
    @Getter
    private MedicamentoDAO medicamentoDAO= new MedicamentoDAOimpl();
    @Getter
    private ObraSocialDAO obraSocialDAO= new ObraSocialDAOimpl();

    public static void reiniciarInstancia(){
        instancia=null;
    }

    public static synchronized Contenedor obtenerInstancia(){
        if(instancia==null){
            instancia=new Contenedor();
        }
        return instancia;
    }


}