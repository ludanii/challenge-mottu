package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.model.Patio;
import br.com.fiap.challengemottu.service.PatioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patios")
@Tag(name = "api-patios")
public class PatioController {
    @Autowired
    private PatioService patioService;

    @Operation(summary = "Cria um novo pátio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pátio criado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patio.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PatioResponse> createPatio(@Valid @RequestBody PatioRequest patio) {
        return new ResponseEntity<>(patioService.save(patio), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pátios")
    @GetMapping
    public ResponseEntity<Page<PatioResponse>> readPatios(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("localizacao").ascending());
        return new ResponseEntity<>(patioService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/patios/1
    // @RequestParam localhost:8080/patios/?id=1
    @Operation(summary = "Retorna um pátio por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pátio encontrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PatioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum pátio encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatioResponse> readPatio(@PathVariable Long id) {
        PatioResponse patio = patioService.findById(id);
        if (patio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patio,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um pátio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pátio atualizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patio.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum pátio encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PatioResponse> updatePatio(@PathVariable Long id,
                                                               @RequestBody PatioRequest patioRequest) {
        PatioResponse patio = patioService.update(patioRequest, id);
        if (patio == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patio,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um pátio por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pátio excluído com sucesso!",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum pátio encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatio(@PathVariable Long id) {
        boolean salvo = patioService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}