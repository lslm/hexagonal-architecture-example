package dev.lslm.demo.adapters.in;

import dev.lslm.demo.configuration.ClienteNotFoundException;
import dev.lslm.demo.domain.models.Cliente;
import dev.lslm.demo.domain.ports.in.ClienteUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteUseCase.criarCliente(cliente);
        return new ResponseEntity(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteUseCase.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteUseCase.obterClientePorId(id);

        if (clienteOptional.isPresent())
            return ResponseEntity.ok(clienteOptional.get());
        else
            throw new ClienteNotFoundException("Cliente não encontrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteUseCase.atualizarCliente(id, cliente);
        return new ResponseEntity(clienteAtualizado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        clienteUseCase.apagar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
