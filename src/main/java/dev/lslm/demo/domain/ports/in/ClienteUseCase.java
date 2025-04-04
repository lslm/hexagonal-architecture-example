package dev.lslm.demo.domain.ports.in;

import dev.lslm.demo.domain.models.Cliente;

import java.util.List;

public interface ClienteUseCase {
    Cliente criarCliente(Cliente cliente);
    List<Cliente> listarClientes();
}
