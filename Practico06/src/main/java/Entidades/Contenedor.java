package Entidades;

import DAO.*;
import Implementaciones.*;
import lombok.Getter;

import java.util.Arrays;

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

    public Contenedor() {
        Medicamento medicamento1 = new Medicamento(0, "Ibuprofeno", 2);
        Medicamento medicamento2 = new Medicamento(1, "Tafirol", 2);
        Medicamento medicamento3 = new Medicamento(2, "Paracetamol", 2);
        this.getMedicamentoDAO().crear(medicamento1);
        this.getMedicamentoDAO().crear(medicamento2);
        this.getMedicamentoDAO().crear(medicamento3);

        Especialidad cirujano = new Especialidad(0, "Cirujano");
        Especialidad pediatra = new Especialidad(1, "Pediatra");
        this.getEspecialidadDAO().crear(cirujano);
        this.getEspecialidadDAO().crear(pediatra);

        ObraSocial osde = new ObraSocial(0, "OSDE");
        ObraSocial swissMedical = new ObraSocial(1, "SwissMedical");
        this.getObraSocialDAO().crear(osde);
        this.getObraSocialDAO().crear(swissMedical);

        Medico medico1 = new Medico(0, "Carlos", "Gomez", cirujano, Arrays.asList(osde), true);
        Medico medico2 = new Medico(1, "María", "Fernandez", pediatra, Arrays.asList(osde), false);
        Medico medico3 = new Medico(2, "Juan", "Perez", cirujano, Arrays.asList(osde), true);
        Medico medico4 = new Medico(3, "Ana", "Rodriguez", pediatra, Arrays.asList(osde), false);
        Medico medico5 = new Medico(4, "Pedro", "Gonzalez", cirujano, Arrays.asList(swissMedical), true);
        Medico medico6 = new Medico(5, "Laura", "Garcia", pediatra, Arrays.asList(swissMedical), false);
        Medico medico7 = new Medico(6, "Lucas", "Fernandez", cirujano, Arrays.asList(swissMedical), true);
        Medico medico8 = new Medico(7, "Sofia", "Rodriguez", pediatra, Arrays.asList(swissMedical), false);
        this.getMedicoDAO().crear(medico1);
        this.getMedicoDAO().crear(medico2);
        this.getMedicoDAO().crear(medico3);
        this.getMedicoDAO().crear(medico4);
        this.getMedicoDAO().crear(medico5);
        this.getMedicoDAO().crear(medico6);
        this.getMedicoDAO().crear(medico7);
        this.getMedicoDAO().crear(medico8);
    }



}