package jmriosp.com.tarea01.controller;

import jmriosp.com.tarea01.dto.VentaDTO;
import jmriosp.com.tarea01.exception.ModeloNotFoundException;
import jmriosp.com.tarea01.model.Venta;
import jmriosp.com.tarea01.service.VentaService;
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
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() throws Exception {
        return new ResponseEntity<>(service.listar().stream()
                .map(Venta -> mapper.map(Venta, VentaDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        return new ResponseEntity<>(mapper.map(service.listarPorId(id), VentaDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody VentaDTO ventaDTORequest) throws Exception {
        VentaDTO VentaDTOResponse = mapper.map(service.registrarTransaccional
                (mapper.map(ventaDTORequest, Venta.class)), VentaDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(VentaDTOResponse.getIdVenta()).toUri();
        return ResponseEntity.created(location).build();
    }

}
