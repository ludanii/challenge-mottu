package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.service.ClienteService;
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
@RequestMapping(value = "/clientes")
@Tag(name = "api-clientes")
public class ClienteController {
        @Autowired
        private ClienteService clienteService;

        @Operation(summary = "Cria um novo cliente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
                        @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos!", content = @Content(schema = @Schema()))
        })
        @PostMapping
        public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest cliente) {
                return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
        }

        @Operation(summary = "Lista todos os clientes")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class)))
        })
        @GetMapping
        public ResponseEntity<List<ClienteResponse>> readClientes() {
                List<ClienteResponse> clientes = clienteService.findAll();
                return new ResponseEntity<>(clientes, HttpStatus.OK);
        }

        @Operation(summary = "Retorna um cliente por ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado para o ID fornecido!", content = @Content(schema = @Schema()))
        })
        @GetMapping("/{id}")
        public ResponseEntity<ClienteResponse> readCliente(@PathVariable Long id) {
                ClienteResponse cliente = clienteService.findById(id);
                if (cliente == null) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(cliente, HttpStatus.OK);
        }

        @Operation(summary = "Atualiza um cliente existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Nenhum cliente encontrado para o ID fornecido!", content = @Content(schema = @Schema()))
        })
        @PutMapping("/{id}")
        public ResponseEntity<ClienteResponse> updateCliente(@PathVariable Long id,
                        @Valid @RequestBody ClienteRequest clienteRequest) {
                ClienteResponse cliente = clienteService.update(clienteRequest, id);
                if (cliente == null) {
                        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(cliente, HttpStatus.OK);
        }

        @Operation(summary = "Exclui um cliente por ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso!", content = @Content(schema = @Schema())),
                        @ApiResponse(responseCode = "400", description = "Nenhum cliente encontrado para o ID fornecido!", content = @Content(schema = @Schema()))
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
                boolean excluido = clienteService.delete(id);
                if (!excluido) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(HttpStatus.OK);
        }
}
