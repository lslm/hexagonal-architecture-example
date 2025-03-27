package dev.lslm.demo.domain.services;

import dev.lslm.demo.domain.models.Cliente;
import dev.lslm.demo.domain.ports.in.ClienteUseCase;
import dev.lslm.demo.domain.ports.out.ClienteRepositoryPort;

public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    @Override
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepositoryPort.salvar(cliente);
    }
}
