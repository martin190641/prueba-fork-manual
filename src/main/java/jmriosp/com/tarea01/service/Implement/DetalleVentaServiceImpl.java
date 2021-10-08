package jmriosp.com.tarea01.service.Implement;


import jmriosp.com.tarea01.model.DetalleVenta;
import jmriosp.com.tarea01.repository.GenericRepository;
import jmriosp.com.tarea01.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaServiceImpl extends GenericCRUDImpl <DetalleVenta, Integer> implements DetalleVentaService {

    @Autowired
    private GenericRepository<DetalleVenta, Integer> repository;

    @Override
    protected GenericRepository<DetalleVenta, Integer> getRepository() {
        return repository;
    }
}
