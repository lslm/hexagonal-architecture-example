package dev.lslm.demo.adapters.out.persistency;

import dev.lslm.demo.domain.models.Cliente;
import dev.lslm.demo.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQLServerAdapter {

    public Cliente salvar(Cliente cliente) {
        System.out.println("Salvando no SQLServer");
        System.out.println("...........");
        cliente.setId(312L);
        return cliente;
    }

    public List<Cliente> buscarClientes() {
        return List.of();
    }
}
