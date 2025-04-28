package dev.lslm.demo.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lslm.demo.adapters.in.NovoClienteRequest;
import dev.lslm.demo.domain.models.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void criarClienteTest() throws Exception {
        var novoCliente = criarCliarCliente();

        assertThat(novoCliente.getNome()).isEqualTo("Lucas");
        assertThat(novoCliente.getEmail()).isEqualTo("lucas@gmail.com");
        assertThat(novoCliente.getTelefone()).isEqualTo("12344");
    }

    @Test
    void buscarClientePorIdTest() throws Exception {
        var novoCliente = criarCliarCliente();

        var resultadoBusca = mvc.perform(get("/api/clientes/{id}", novoCliente.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var cliente = mapper.readValue(resultadoBusca.getResponse().getContentAsString(), Cliente.class);
        assertThat(cliente.getNome()).isEqualTo("Lucas");
        assertThat(cliente.getEmail()).isEqualTo("lucas@gmail.com");
        assertThat(cliente.getTelefone()).isEqualTo("12344");
    }

    @Test
    void atualizaClienteTest() throws Exception {
        var novoCliente = criarCliarCliente();

        var clienteUpdateRequest = new NovoClienteRequest("Lucas Santos", "lucassantos@gmail.com", "0000");
        var resultadoAtualizacao = mvc.perform(put("/api/clientes/{id}", novoCliente.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clienteUpdateRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        var clienteAtualizado = mapper.readValue(resultadoAtualizacao.getResponse().getContentAsString(), Cliente.class);
        assertThat(clienteAtualizado.getNome()).isEqualTo("Lucas Santos");
        assertThat(clienteAtualizado.getEmail()).isEqualTo("lucassantos@gmail.com");
        assertThat(clienteAtualizado.getTelefone()).isEqualTo("0000");
    }

    @Test
    void deletarClienteTest() throws Exception {
        var novoCliente = criarCliarCliente();

        mvc.perform(delete("/api/clientes/{id}", novoCliente.getId()))
                .andExpect(status().isNoContent());
    }

    Cliente criarCliarCliente() throws Exception {
        NovoClienteRequest novoClienteRequest = new NovoClienteRequest("Lucas", "lucas@gmail.com", "12344");

        var resultado = mvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(novoClienteRequest)))
                .andReturn();

        return mapper.readValue(resultado.getResponse().getContentAsString(), Cliente.class);
    }
}
