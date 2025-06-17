package com.crgden.api.controllers;

import com.crgden.api.dto.DenunciaDTO;
import com.crgden.api.entities.Denuncia;
import com.crgden.api.services.DenunciaService;
import jakarta.validation.Valid;
import jakarta.validation.metadata.ValidateUnwrappedValue;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    // Retorna todas as denuncias
    @GetMapping
    public ResponseEntity<List<DenunciaDTO>> denuncias() {
        List<DenunciaDTO> registros = denunciaService.selecionarDenuncias();
        return ResponseEntity.ok(registros);
    }

    // Retorna denuncia pelo protocolo da denuncia
    @GetMapping(value = "/{protocolo}")
    public ResponseEntity<DenunciaDTO> denunciaPorProtocolo(@PathVariable Long protocolo) {
        DenunciaDTO dto = denunciaService.selecionarDenunciaPorProtocolo(protocolo);
        return ResponseEntity.ok(dto);
    }

    // Registra uma nova denuncia
    @PostMapping
    public ResponseEntity<DenunciaDTO> novaDenuncia(@Valid @RequestBody DenunciaDTO dto) {
        dto = denunciaService.registrarDenuncia(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{protocolo}")
                .buildAndExpand(dto.getProtocolo()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    // Atualiza a denuncia pelo protocolo
    @PutMapping(value = "/{protocolo}")
    public ResponseEntity<DenunciaDTO> atualizarDenunciaFeita(@PathVariable Long protocolo, @Valid @RequestBody DenunciaDTO dto) {
        dto = denunciaService.atualizarDenuncia(protocolo, dto);
        return ResponseEntity.ok(dto);
    }

    // Deleta a denuncia feita pelo usuário
    @DeleteMapping(value = "/{protocolo}")
    public ResponseEntity<Void> deletarDenunciaFeita(@PathVariable Long protocolo) {
        denunciaService.deletarDenuncia(protocolo);
        return ResponseEntity.noContent().build();
    }
}