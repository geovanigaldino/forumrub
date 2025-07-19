package com.alurachallenge.forumhub.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(

        @NotNull
        String username,
        @NotNull
        String senha,
        @NotNull
        String curso
) {
}
