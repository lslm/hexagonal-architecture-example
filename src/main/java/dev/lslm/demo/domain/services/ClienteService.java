package dev.lslm.demo.domain.services;

import dev.lslm.demo.configuration.ClienteNotFoundException;
import dev.lslm.demo.domain.models.Cliente;
import dev.lslm.demo.domain.ports.in.ClienteUseCase;
import dev.lslm.demo.domain.ports.out.ClienteRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    @Override
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepositoryPort.buscarClientes();
    }

    @Override
    public Optional<Cliente> obterClientePorId(Long id) {
        return clienteRepositoryPort.buscarPorId(id);
    }

    @Override
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = obterClientePorId(id).orElseThrow(() -> new ClienteNotFoundException("Cliente não econtrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());

        return clienteRepositoryPort.salvar(clienteExistente);
    }

    @Override
    public void apagar(Long id) {
        Optional<Cliente> clienteOptional = obterClientePorId(id);

        if (clienteOptional.isPresent())
            clienteRepositoryPort.apagar(id);
        else
            throw new ClienteNotFoundException("Cliente não encontrado");
    }
}
