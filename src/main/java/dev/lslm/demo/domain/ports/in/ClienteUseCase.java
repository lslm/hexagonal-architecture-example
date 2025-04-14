package dev.lslm.demo.domain.ports.in;

import dev.lslm.demo.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteUseCase {
    Cliente criarCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Optional<Cliente> obterClientePorId(Long id);
    Cliente atualizarCliente(Long id, Cliente cliente);
    void apagar(Long id);
}
