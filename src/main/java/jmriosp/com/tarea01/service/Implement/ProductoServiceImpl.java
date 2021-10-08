package jmriosp.com.tarea01.service.Implement;


import jmriosp.com.tarea01.model.Producto;
import jmriosp.com.tarea01.repository.GenericRepository;
import jmriosp.com.tarea01.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends GenericCRUDImpl <Producto, Integer> implements ProductoService {

    @Autowired
    private GenericRepository<Producto, Integer> repository;

    @Override
    protected GenericRepository<Producto, Integer> getRepository() {
        return repository;
    }
}
