package TestIMPL;

import Entidades.Farmacia;
import Entidades.Medicamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FarmaciaTest {
    private Farmacia farmacia;

    @BeforeEach
    void setUp() {
        // Configurar objetos necesarios para cada prueba
        farmacia = new Farmacia(new ArrayList<>()); // Inicializa la farmacia con una lista vacía
    }

    @Test
    void agregarMedicamentos() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Ibuprofeno", 10);

        // Ejecutar el método que queremos probar
        farmacia.agregarMedicamento(medicamento1);

        // Verificar el resultado esperado
        assertTrue(farmacia.getStock().contains(medicamento1));
    }

    @Test
    void restarMedicamentos() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Medicamento1", 10);
        farmacia.agregarMedicamento(medicamento1); // Agrega un medicamento al stock

        // Ejecutar el método que queremos probar
        farmacia.restarMedicamentos(medicamento1, 5); // Resta 5 unidades del medicamento

        // Verificar el resultado esperado
        assertEquals(5, medicamento1.getCantidad()); // Verifica que la cantidad del medicamento se haya reducido correctamente
    }

    @Test
    void tieneSuficienteStock_Suficiente() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Medicamento1", 10);
        farmacia.agregarMedicamento(medicamento1); // Agrega un medicamento al stock

        // Ejecutar el método que queremos probar
        boolean tieneSuficienteStock = farmacia.tieneSuficienteStock(medicamento1);

        // Verificar el resultado esperado
        assertTrue(tieneSuficienteStock); // Debería tener suficiente stock
    }

    @Test
    void tieneSuficienteStock_Insuficiente() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Medicamento1", 10);

        // Ejecutar el método que queremos probar
        boolean tieneSuficienteStock = farmacia.tieneSuficienteStock(medicamento1);

        // Verificar el resultado esperado
        assertFalse(tieneSuficienteStock); // No debería tener suficiente stock
    }

    @Test
    void agregarMedicamentos_MedicamentoExistente() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Medicamento1", 10);
        farmacia.agregarMedicamento(medicamento1); // Agrega un medicamento al stock

        // Ejecutar el método que queremos probar
        farmacia.agregarMedicamento(medicamento1); // Agrega el mismo medicamento al stock

        // Verificar el resultado esperado
        assertEquals(1, farmacia.getStock().size()); // No debería agregar el medicamento nuevamente
    }

    @Test
    void restarMedicamentos_MedicamentoInexistente() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Medicamento1", 10);

        // Ejecutar el método que queremos probar
        farmacia.restarMedicamentos(medicamento1, 5); // Resta 5 unidades de un medicamento inexistente

        // Verificar el resultado esperado
        assertEquals(10, medicamento1.getCantidad()); // No debería restar unidades de un medicamento inexistente
    }

    @Test
    void restarMedicamentos_CantidadInsuficiente() {
        // Configurar estado inicial
        Medicamento medicamento1 = new Medicamento(1, "Medicamento1", 10);
        farmacia.agregarMedicamento(medicamento1); // Agrega un medicamento al stock

        // Verificar que el método lance la excepción adecuada
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            farmacia.restarMedicamentos(medicamento1, 15); // Intenta restar 15 unidades de un medicamento con cantidad insuficiente
        });

        // Verificar que la excepción tenga el mensaje correcto
        assertEquals("La cantidad a reducir es inválida", exception.getMessage());

        // Verificar que la cantidad del medicamento no ha cambiado
        assertEquals(10, medicamento1.getCantidad());
    }
}
