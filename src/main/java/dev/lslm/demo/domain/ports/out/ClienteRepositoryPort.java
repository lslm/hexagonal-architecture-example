package dev.lslm.demo.domain.ports.out;

import dev.lslm.demo.domain.models.Cliente;

import java.util.List;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    List<Cliente> buscarClientes();
}
