package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
import br.com.fiap.challengemottu.service.MotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/motos")
@Tag(name = "api-motos")
public class MotoController {
        @Autowired
        private MotoService motoService;

        @PostMapping
        public ResponseEntity<MotoResponse> createMoto(@Valid @RequestBody MotoRequest moto) {
                MotoResponse response = motoService.save(moto);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<MotoResponse>> readMotos() {
                return ResponseEntity.ok(motoService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<MotoResponse> readMoto(@PathVariable Long id) {
                MotoResponse moto = motoService.findById(id);
                return ResponseEntity.ok(moto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<MotoResponse> updateMoto(
                        @PathVariable("id") Long id,
                        @Valid @RequestBody MotoRequest motoRequest) {

                MotoResponse moto = motoService.update(motoRequest, id);
                return ResponseEntity.ok(moto);
        }

        @Operation(summary = "Exclui uma moto por ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Moto excluída com sucesso!", content = @Content(schema = @Schema())),
                        @ApiResponse(responseCode = "404", description = "Nenhuma moto encontrada para o ID fornecido!", content = @Content(schema = @Schema()))
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMoto(@PathVariable("id") Long id) {
                motoService.delete(id);
                return ResponseEntity.noContent().build();
        }
}
