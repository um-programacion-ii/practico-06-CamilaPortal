package TestIMPL;
import Entidades.*;
import Servicios.GestionFarmaciaService;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GestionFarmaciaServiceTest {

    private Farmacia farmacia;
    private Drogueria drogueria;
    private GestionFarmaciaService gestionFarmaciaService;

    @BeforeEach
    void setUp() {
        // Configurar objetos necesarios para cada prueba
        farmacia = new Farmacia(new ArrayList<>());
        drogueria = new Drogueria();
        gestionFarmaciaService = GestionFarmaciaService.obtenerInstancia(farmacia, drogueria);
    }


    @Test
    void procesarReceta_StockSuficiente() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Ibuprofeno", 10);
        farmacia.agregarMedicamento(medicamento1);

        // Crear una instancia de Receta con los datos necesarios
        Receta receta = new Receta(1, new Paciente(), new Medico(), List.of(medicamento1));

        // Ejecutar la función bajo prueba
        gestionFarmaciaService.procesarReceta(receta);

        // Verificar el resultado esperado
        assertEquals(1, farmacia.getStock().size()); // El medicamento debe ser retirado del stock
        assertEquals(0, farmacia.getStock().get(0).getCantidad());
    }

    @Test
    void procesarReceta_StockInsuficiente() {
        // Configurar estado inicial
        Medicamento medicamento2 = new Medicamento(2, "Medicamento2", 10);

        // Crear una instancia de Receta con los datos necesarios
        Receta receta = new Receta(1, new Paciente(), new Medico(), List.of(medicamento2));

        // Ejecutar la función bajo prueba
        gestionFarmaciaService.procesarReceta(receta);

        // Verificar el resultado esperado
        assertEquals(1, farmacia.getStock().size()); // El medicamento no pudo ser retirado del stock
    }

    @Test
    void procesarReceta_StockInsuficiente_Drogueria() {
        // Configurar estado inicial
        Medicamento medicamento3 = new Medicamento(3, "Medicamento3", 10);
        Medicamento medicamento4 = new Medicamento(4, "Medicamento4", 10);
        farmacia.agregarMedicamento(medicamento4);

        // Crear una instancia de Receta con los datos necesarios
        Receta receta = new Receta(1, new Paciente(), new Medico(), List.of(medicamento3));

        // Ejecutar la función bajo prueba
        gestionFarmaciaService.procesarReceta(receta);

        // Verificar el resultado esperado
        assertEquals(2, farmacia.getStock().size());
        assertTrue(farmacia.getStock().contains(medicamento3)); // Verificar que el medicamento3 se agregó al stock
        assertEquals(10, farmacia.getStock().get(1).getCantidad());

    }

}

