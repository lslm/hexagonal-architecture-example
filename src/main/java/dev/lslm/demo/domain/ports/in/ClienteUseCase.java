package dev.lslm.demo.domain.ports.in;

import dev.lslm.demo.domain.models.Cliente;

public interface ClienteUseCase {
    Cliente criarCliente(Cliente cliente);
}
