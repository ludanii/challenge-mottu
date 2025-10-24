package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.dto.LoginRequest;
import br.com.fiap.challengemottu.dto.LoginResponse;
import br.com.fiap.challengemottu.service.FuncionarioService;
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
@RequestMapping(value = "/funcionarios")
@Tag(name = "api-funcionarios")
public class FuncionarioController {
        @Autowired
        private FuncionarioService funcionarioService;

        @PostMapping
        public ResponseEntity<FuncionarioResponse> createFuncionario(@Valid @RequestBody FuncionarioRequest funcionario) {
                FuncionarioResponse response = funcionarioService.save(funcionario);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<FuncionarioResponse>> readFuncionarios() {
                return ResponseEntity.ok(funcionarioService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<FuncionarioResponse> readFuncionario(@PathVariable("id") Long id) {
                FuncionarioResponse funcionario = funcionarioService.findById(id);
                return ResponseEntity.ok(funcionario);
        }

        @PutMapping("/{id}")
        public ResponseEntity<FuncionarioResponse> updateFuncionario(@PathVariable("id") Long id,
                        @RequestBody FuncionarioRequest funcionarioRequest) {
                FuncionarioResponse funcionario = funcionarioService.update(funcionarioRequest, id);
                return ResponseEntity.ok(funcionario);
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso!"),
                        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado para o ID fornecido!")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteFuncionario(@PathVariable("id") Long id) {
                funcionarioService.delete(id);
                return ResponseEntity.noContent().build();
        }

        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
                LoginResponse response = funcionarioService.login(
                                loginRequest.usuario(),
                                loginRequest.senha(),
                                loginRequest.tipo());
                return ResponseEntity.ok(response);
        }
}
