package dev.lslm.demo.domain.ports.out;

import dev.lslm.demo.domain.models.Cliente;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
}
