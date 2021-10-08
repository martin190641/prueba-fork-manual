package jmriosp.com.tarea01.service.Implement;


import jmriosp.com.tarea01.model.Venta;
import jmriosp.com.tarea01.repository.GenericRepository;
import jmriosp.com.tarea01.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl extends GenericCRUDImpl<Venta, Integer> implements VentaService {

    @Autowired
    private GenericRepository<Venta, Integer> repository;

    @Override
    protected GenericRepository<Venta, Integer> getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public Venta registrarTransaccional(Venta venta) throws Exception {
        System.out.println(venta);
        venta.getDetalleVentas().forEach(detalleVenta -> detalleVenta.setVenta(venta));
        return repository.save(venta);
    }
}
