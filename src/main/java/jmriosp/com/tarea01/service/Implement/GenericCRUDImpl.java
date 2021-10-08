package jmriosp.com.tarea01.service.Implement;

import jmriosp.com.tarea01.repository.GenericRepository;
import jmriosp.com.tarea01.service.GenericCRUD;

import java.util.List;

public abstract class GenericCRUDImpl<T, ID> implements GenericCRUD<T, ID> {

    protected abstract GenericRepository<T, ID> getRepository();

    @Override
    public T registrar(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public T modificar(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public T listarPorId(ID id) throws Exception {
        return getRepository().getById(id);
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepository().deleteById(id);
    }

    @Override
    public boolean existePorId(ID id) throws Exception {
        return getRepository().existsById(id);
    }
}
