package dev.lslm.demo.adapters.in;

public record NovoClienteRequest(
        String nome,
        String email,
        String telefone
) {
}
