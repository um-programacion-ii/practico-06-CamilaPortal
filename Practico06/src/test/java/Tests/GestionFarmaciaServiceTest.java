package Tests;
import Entidades.*;
import Servicios.GestionFarmaciaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GestionFarmaciaServiceTest {

    private Farmacia farmacia;
    private Drogueria drogueria;
    private GestionFarmaciaService gestionFarmaciaService;

    @BeforeEach
    public void reiniciarSingleton() {
        GestionFarmaciaService.reiniciarInstancia();
    }

    @Test
    void procesarReceta_StockSuficiente(){
        farmacia = new Farmacia(new ArrayList<>());
        drogueria = new Drogueria();
        gestionFarmaciaService = GestionFarmaciaService.obtenerInstancia(farmacia, drogueria);

        Medicamento medicamento1 = new Medicamento(1, "Ibuprofeno", 10);
        gestionFarmaciaService.getFarmacia().agregarMedicamento(medicamento1);

        Receta receta = new Receta(1, new Paciente(), new Medico(), List.of(medicamento1));

        gestionFarmaciaService.procesarReceta(receta);

        assertEquals(1, gestionFarmaciaService.getFarmacia().getStock().size());
        assertEquals(0, gestionFarmaciaService.getFarmacia().getStock().get(0).getCantidad());
    }

    @Test
    void procesarReceta_StockInsuficiente(){
        farmacia = new Farmacia(new ArrayList<>());
        drogueria = new Drogueria();
        gestionFarmaciaService = GestionFarmaciaService.obtenerInstancia(farmacia, drogueria);

        Medicamento medicamento2 = new Medicamento(2, "Medicamento2", 10);

        Receta receta = new Receta(1, new Paciente(), new Medico(), List.of(medicamento2));

        gestionFarmaciaService.procesarReceta(receta);

        assertEquals(1, gestionFarmaciaService.getFarmacia().getStock().size());
    }

    @Test
    void procesarReceta_StockInsuficiente_Drogueria() {
        farmacia = new Farmacia(new ArrayList<>());
        drogueria = new Drogueria();
        gestionFarmaciaService = GestionFarmaciaService.obtenerInstancia(farmacia, drogueria);

        Medicamento medicamento3 = new Medicamento(3, "Medicamento3", 10);
        Medicamento medicamento4 = new Medicamento(4, "Medicamento4", 10);
        gestionFarmaciaService.getFarmacia().agregarMedicamento(medicamento4);

        Receta receta = new Receta(1, new Paciente(), new Medico(), List.of(medicamento3));

        gestionFarmaciaService.procesarReceta(receta);
        assertEquals(2, gestionFarmaciaService.getFarmacia().getStock().size());
        assertEquals(0, gestionFarmaciaService.getFarmacia().getStock().get(1).getCantidad());
        assertTrue(gestionFarmaciaService.getFarmacia().getStock().contains(medicamento3));

    }

}

