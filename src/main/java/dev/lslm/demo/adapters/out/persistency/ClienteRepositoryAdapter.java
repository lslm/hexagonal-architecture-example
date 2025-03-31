package dev.lslm.demo.adapters.out.persistency;

import dev.lslm.demo.domain.models.Cliente;
import dev.lslm.demo.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final SpringDataClienteRepository springDataClienteRepository;

    public ClienteRepositoryAdapter(SpringDataClienteRepository springDataClienteRepository) {
        this.springDataClienteRepository = springDataClienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        ClienteEntity persistedEntity = springDataClienteRepository.save(entity);
        return toDomain(persistedEntity);
    }

    @Override
    public List<Cliente> buscarClientes() {
        return springDataClienteRepository
                .findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }

    private Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone()
        );
    }
}
