package dev.lslm.demo.domain.ports.out;

import dev.lslm.demo.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    List<Cliente> buscarClientes();
    Optional<Cliente> buscarPorId(Long id);
    void apagar(Long id);
}
