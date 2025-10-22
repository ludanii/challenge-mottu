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

    @PostMapping
    public ResponseEntity<PatioResponse> createPatio(@Valid @RequestBody PatioRequest patio) {
        patio = patioService.save(patio);
        return new ResponseEntity<>(patio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatioResponse>> readPatios() {
        return ResponseEntity.ok(patioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioResponse> readPatio(@PathVariable("id") Long id) {
        PatioResponse patio = patioService.findById(id);
        return ResponseEntity.ok(patio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioResponse> updatePatio(
            @PathVariable("id") Long id,
            @Valid @RequestBody PatioRequest patioRequest) {
        PatioResponse patio = patioService.update(patioRequest, id);
        return ResponseEntity.ok(patio);
    }

    @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso!"),
                        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado para o ID fornecido!")
        })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatio(@PathVariable("id") Long id) {
        boolean deleted = patioService.delete(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}