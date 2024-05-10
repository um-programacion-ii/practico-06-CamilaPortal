package Servicios;

import Entidades.Drogueria;
import Entidades.Farmacia;
import Entidades.Medicamento;
import Entidades.Receta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionFarmaciaService {
    private static GestionFarmaciaService instancia;
    private Farmacia farmacia;
    private Drogueria drogueria;

    private GestionFarmaciaService(Farmacia farmacia, Drogueria drogueria) {
        this.farmacia = farmacia;
        this.drogueria = drogueria;
    }

    public static synchronized GestionFarmaciaService obtenerInstancia(Farmacia farmacia, Drogueria drogueria) {
        if (instancia == null) {
            instancia = new GestionFarmaciaService(farmacia, drogueria);
        }
        return instancia;
    }

    public void procesarReceta(Receta receta) {
        for (Medicamento medicamento : receta.getMedicamentos()) {
            if (!farmacia.tieneSuficienteStock(medicamento)) {
                // Si la farmacia no tiene suficiente stock, solicita los medicamentos a la droguería
                List<Medicamento> medicamentosRecibidos = drogueria.proporcionarMedicamentos(List.of(medicamento));
                for (Medicamento nuevoMedicamento : medicamentosRecibidos) {
                    farmacia.agregarMedicamento(nuevoMedicamento);
                }
            }
            // Resta la cantidad de medicamentos de la farmacia
            farmacia.restarMedicamentos(medicamento, medicamento.getCantidad());
        }
    }
}
