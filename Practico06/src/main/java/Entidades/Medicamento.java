package Entidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medicamento {
    private int id;
    private String nombre;
    private int cantidad;

    public void reducirCantidad(int cantidadAReducir) {
        if (cantidadAReducir >= 0 && this.cantidad >= cantidadAReducir) {
            this.cantidad -= cantidadAReducir;
        } else {
            throw new IllegalArgumentException("La cantidad a reducir es inválida");
        }
    }

    public void aumentarCantidad(int cantidadAAumentar) {
        if (cantidadAAumentar >= 0) {
            this.cantidad += cantidadAAumentar;
        } else {
            throw new IllegalArgumentException("La cantidad a aumentar es inválida");
        }
    }
}
