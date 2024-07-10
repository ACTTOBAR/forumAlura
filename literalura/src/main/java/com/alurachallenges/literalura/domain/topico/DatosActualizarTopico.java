package com.alurachallenges.literalura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id,
                                    String titulo,
                                    String mensaje
                                    ) {
}
