package br.com.fiap.challengemottu.controller;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "api-clientes")
public class ClienteController {
        @Autowired
        private ClienteService clienteService;

        @PostMapping
        public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest cliente) {
                ClienteResponse clienteResponse = clienteService.save(cliente);
                return ResponseEntity.ok(clienteResponse);
        }

        @GetMapping
        public ResponseEntity<List<ClienteResponse>> readClientes() {
                return ResponseEntity.ok(clienteService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ClienteResponse> readCliente(@PathVariable("id") Long id) {
                ClienteResponse cliente = clienteService.findById(id);
                return ResponseEntity.ok(cliente);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ClienteResponse> updateCliente(@PathVariable("id") Long id,
                        @Valid @RequestBody ClienteRequest clienteRequest) {
                ClienteResponse cliente = clienteService.update(clienteRequest, id);
                return ResponseEntity.ok(cliente);
        }

        @Operation(summary = "Exclui um cliente por ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso!", content = @Content(schema = @Schema())),
                        @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado para o ID fornecido!", content = @Content(schema = @Schema()))
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
                clienteService.delete(id);
                return ResponseEntity.noContent().build();
        }
}
