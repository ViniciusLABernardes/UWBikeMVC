package br.com.UWBike.dto;


import br.com.UWBike.model.MotoPatio;
import jakarta.validation.constraints.*;

import java.util.List;

public class PatioRequestDto {
    public PatioRequestDto(){

    }
    public PatioRequestDto(String logradouro, int numero, String complemento, String cep, String cidade, String uf, String pais, int lotacao) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
        this.lotacao = lotacao;
    }

    @NotBlank(message = "O logradouro é obrigatório")
    @Size(min = 6, max = 450, message = "O logradouro deve ter entre 6 e 450 caracteres")
    private String logradouro;

    @NotNull(message = "O numero é obrigatório")
    @Min(value = 1, message = "O número deve ser no mínimo 1")
    @Max(value = 999999, message = "O número deve ser no máximo 7 dígitos")
    private int numero;

    @Size( max = 255, message = "O complemento deve ter no máximo 255 caracteres")
    private String complemento;

    @NotBlank(message = "O cep é obrigatório")
    //@Size(min = 1, max = 20, message = "O cep deve ter entre 5 e 15 caracteres")
    private String cep;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(min = 1, max = 100, message = "A cidade deve ter entre 3 e 100 caracteres")
    private String cidade;

    @NotBlank(message = "O UF é obrigatório")
    @Size(min = 1, max = 7, message = "O UF deve ter entre 1 e 6 caracteres")
    private String uf;

    @NotBlank(message = "O pais é obrigatório")
    @Size(min = 1, max = 100, message = "O pais deve ter entre 3 e 100 caracteres")
    private String pais;

    @NotNull(message = "A lotação máxima é obrigatória")
    @Min(value = 1, message = "a lotação deve ser no minimo de: 1")
    private int lotacao;

    private List<MotoPatio> entradas;


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public List<MotoPatio> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<MotoPatio> entradas) {
        this.entradas = entradas;
    }

}
