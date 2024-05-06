package DAO;

import java.util.List;

public interface DAO<T> {
    List<T> listarTodos();

    T leerPorId(int id);

    void crear(T t);

    void actualizar(T t);

    void eliminar(int id);
}
