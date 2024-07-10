package com.alurachallenges.literalura.controller;

import com.alurachallenges.literalura.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico (@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Topico topico = repository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datos = new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 6) Pageable paginacion){
        return ResponseEntity.ok(repository.findByStatusTrue(paginacion)
                .map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity <DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        DatosRespuestaTopico datos = new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha());
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    //Borrado Logico
    public ResponseEntity <DatosRespuestaTopico> eliminarTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity <DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarMedico){
        Topico topico = repository.getReferenceById(datosActualizarMedico.id());
        topico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha()));
    }

}
