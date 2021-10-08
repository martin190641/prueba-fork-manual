package jmriosp.com.tarea01.controller;

import jmriosp.com.tarea01.dto.ProductoDTO;
import jmriosp.com.tarea01.exception.ModeloNotFoundException;
import jmriosp.com.tarea01.model.Producto;
import jmriosp.com.tarea01.service.ProductoService;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() throws Exception {
        return new ResponseEntity<>(service.listar().stream()
                .map(Producto -> mapper.map(Producto, ProductoDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        return new ResponseEntity<>(mapper.map(service.listarPorId(id), ProductoDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ProductoDTO productoDTORequest) throws Exception {
        ProductoDTO productoDTOResponse = mapper.map(service.registrar
                (mapper.map(productoDTORequest, Producto.class)), ProductoDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productoDTOResponse.getIdProducto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> modificar(@Valid @RequestBody ProductoDTO productoDTORequest) throws Exception {
        if (!service.existePorId(productoDTORequest.getIdProducto()))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + productoDTORequest.getIdProducto());
        return new ResponseEntity<>(mapper.map(
                service.modificar(mapper.map(productoDTORequest, Producto.class)), ProductoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ProductoDTO> listarHateoasPorId(@PathVariable("id") Integer id) throws Exception {
        if (!service.existePorId(id))
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);

        EntityModel<ProductoDTO> recurso = EntityModel.of(mapper.map(service.listarPorId(id), ProductoDTO.class));
        //localhost:8080/Producto/id
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarHateoasPorId(id));
        recurso.add(link1.withRel("Producto-recurso1"));
        recurso.add(link2.withRel("Producto-recurso2"));

        return recurso;
    }

}
