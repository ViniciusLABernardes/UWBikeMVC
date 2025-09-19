package br.com.UWBike.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class FuncionarioRequestDto {

    @Data
    public class FuncionarioDTO {


        @NotBlank
        private String nomeFunc;

        @NotBlank
        private String cpf;

        @NotNull
        private Double salario;

        @NotBlank
        private String cargo;

        @NotBlank
        private String login;

        @NotBlank
        private String senha;

        private Long idPatio;
    }

}
