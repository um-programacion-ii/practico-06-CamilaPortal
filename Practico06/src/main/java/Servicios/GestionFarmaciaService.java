package Servicios;

import Entidades.Drogueria;
import Entidades.Farmacia;
import Entidades.Medicamento;
import Entidades.Receta;

import java.util.List;


import lombok.Data;

@Data
public class GestionFarmaciaService {
    private static GestionFarmaciaService instancia;
    private Farmacia farmacia;
    private Drogueria drogueria;

    public static void reiniciarInstancia() {
        instancia = null;
    }

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

                List<Medicamento> medicamentosRecibidos = drogueria.proporcionarMedicamentos(List.of(medicamento));
                for (Medicamento nuevoMedicamento : medicamentosRecibidos) {
                    farmacia.agregarMedicamento(nuevoMedicamento);
                }
            }

            farmacia.restarMedicamentos(medicamento, medicamento.getCantidad());
        }
    }
}
