package jmriosp.com.tarea01.service.Implement;

import jmriosp.com.tarea01.model.Persona;
import jmriosp.com.tarea01.repository.GenericRepository;
import jmriosp.com.tarea01.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends GenericCRUDImpl <Persona, Integer> implements PersonaService {

    @Autowired
    private GenericRepository<Persona, Integer> repository;

    @Override
    protected GenericRepository<Persona, Integer> getRepository() {
        return repository;
    }
}
