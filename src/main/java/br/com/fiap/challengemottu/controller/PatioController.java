package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.service.PatioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patios")
@Tag(name = "api-patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    @Operation(summary = "Cria um novo pátio")
    @PostMapping
    public ResponseEntity<PatioResponse> createPatio(@Valid @RequestBody PatioRequest patio) {
        return new ResponseEntity<>(patioService.save(patio), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pátios")
    @GetMapping
    public ResponseEntity<List<PatioResponse>> readPatios() {
        List<PatioResponse> patios = patioService.findAll();
        return new ResponseEntity<>(patios, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um pátio por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PatioResponse> readPatio(@PathVariable Long id) {
        PatioResponse patio = patioService.findById(id);
        if (patio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patio, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um pátio existente")
    @PutMapping("/{id}")
    public ResponseEntity<PatioResponse> updatePatio(@PathVariable Long id,
            @Valid @RequestBody PatioRequest patioRequest) {
        PatioResponse patio = patioService.update(patioRequest, id);
        if (patio == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patio, HttpStatus.OK);
    }

    @Operation(summary = "Exclui um pátio por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatio(@PathVariable Long id) {
        boolean deleted = patioService.delete(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}