package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.service.FuncionarioService;
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
@RequestMapping(value = "/funcionarios")
@Tag(name = "api-funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    // CREATE, READ, UPDATE, DELETE
    // POST, GET, PUT, DELETE

    @Operation(summary = "Cria um novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<FuncionarioResponse> createFuncionario(@Valid @RequestBody FuncionarioRequest funcionario) {
        return new ResponseEntity<>(funcionarioService.save(funcionario), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os funcionários")
    @GetMapping
    public ResponseEntity<Page<FuncionarioResponse>> readFuncionarios(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest
                .of(pageNumber, 2, Sort.by("nomeUsuario").ascending());
        return new ResponseEntity<>(funcionarioService.findAll(pageable), HttpStatus.OK);
    }

    // @PathVariable localhost:8080/funcionarios/1
    // @RequestParam localhost:8080/funcionarios/?id=1
    @Operation(summary = "Retorna um funcionário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FuncionarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum funcionário encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> readFuncionario(@PathVariable Long id) {
        FuncionarioResponse funcionario = funcionarioService.findById(id);
        if (funcionario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(funcionario,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um funcionário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário atualizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum funcionário encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> updateFuncionario(@PathVariable Long id,
                                                               @RequestBody FuncionarioRequest funcionarioRequest) {
        FuncionarioResponse funcionario = funcionarioService.update(funcionarioRequest, id);
        if (funcionario == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(funcionario,HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um funcionário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário excluído com sucesso!",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum funcionário encontrado para o ID fornecido!",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        boolean salvo = funcionarioService.delete(id);
        if (!salvo) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}