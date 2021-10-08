package jmriosp.com.tarea01.controller;

import jmriosp.com.tarea01.dto.PersonaDTO;
import jmriosp.com.tarea01.exception.ModeloNotFoundException;
import jmriosp.com.tarea01.model.Persona;
import jmriosp.com.tarea01.service.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> listar() throws Exception {
        return new ResponseEntity<>(service.listar().stream()
                .map(persona -> mapper.map(persona, PersonaDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        return new ResponseEntity<>(mapper.map(service.listarPorId(id), PersonaDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody PersonaDTO personaDTORequest) throws Exception {
        PersonaDTO personaDTOResponse = mapper.map(service.registrar
                (mapper.map(personaDTORequest, Persona.class)), PersonaDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(personaDTOResponse.getIdPersona()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<PersonaDTO> modificar(@RequestBody PersonaDTO personaDTORequest) throws Exception {
        if (!service.existePorId(personaDTORequest.getIdPersona()))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + personaDTORequest.getIdPersona());
        return new ResponseEntity<>(mapper.map(
                service.modificar(mapper.map(personaDTORequest, Persona.class)), PersonaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<PersonaDTO> listarHateoasPorId(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);

        EntityModel<PersonaDTO> recurso = EntityModel.of(mapper.map(service.listarPorId(id), PersonaDTO.class));
        //localhost:8080/persona/id
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarHateoasPorId(id));
        recurso.add(link1.withRel("persona-recurso1"));
        recurso.add(link2.withRel("persona-recurso2"));

        return recurso;
    }

}
