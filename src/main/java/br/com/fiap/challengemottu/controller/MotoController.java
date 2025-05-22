package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
import br.com.fiap.challengemottu.model.Moto;
import br.com.fiap.challengemottu.service.MotoService;
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
@RequestMapping(value = "/motos")
@Tag(name = "api-motos")
public class MotoController {
    @Autowired
    private MotoService motoService;

    // CREATE, READ, UPDATE, DELETE
    // POST, GET, PUT, DELETE

    @Operation(summary = "Cria uma nova moto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Moto criada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Moto.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<MotoResponse> createMoto(@Valid @RequestBody MotoRequest moto) {
        return new ResponseEntity<>(motoService.save(moto), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as motos")
    @GetMapping
    public ResponseEntity<Page<MotoResponse>> readMotos(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("modelo").ascending());
        return new ResponseEntity<>(motoService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/motos/1
    // @RequestParam localhost:8080/motos/?id=1
    @Operation(summary = "Retorna uma moto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moto encontrada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MotoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma moto encontrada para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> readMoto(@PathVariable Long id) {
        MotoResponse moto = motoService.findById(id);
        if (moto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(moto,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma moto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Moto atualizada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Moto.class))),
            @ApiResponse(responseCode = "400", description = "Nenhuma moto encontrada para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<MotoResponse> updateMoto(@PathVariable Long id,
                                                               @RequestBody MotoRequest motoRequest) {
        MotoResponse moto = motoService.update(motoRequest, id);
        if (moto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(moto,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma moto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moto excluída com sucesso!",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhuma moto encontrada para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoto(@PathVariable Long id) {
        boolean salvo = motoService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}