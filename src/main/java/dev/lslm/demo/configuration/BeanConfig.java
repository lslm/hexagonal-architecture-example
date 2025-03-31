package dev.lslm.demo.configuration;

import dev.lslm.demo.adapters.out.persistency.ClienteRepositoryAdapter;
import dev.lslm.demo.adapters.out.persistency.SQLServerAdapter;
import dev.lslm.demo.domain.ports.in.ClienteUseCase;
import dev.lslm.demo.domain.ports.out.ClienteRepositoryPort;
import dev.lslm.demo.domain.services.ClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ClienteUseCase clienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }

    @Bean
    public ClienteRepositoryPort clienteRepositoryPort(ClienteRepositoryAdapter adapter) {
        return adapter;
    }
}
