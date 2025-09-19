package br.com.UWBike.dto;



import br.com.UWBike.model.MotoPatio;

import java.util.List;

public class PatioResponseDto {

    public PatioResponseDto(){

    }
    public PatioResponseDto(long idPatio,String logradouro, int numero, String complemento, String cep, String cidade, String uf, String pais, int lotacao) {
        this.idPatio = idPatio;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
        this.lotacao = lotacao;
    }

    private long idPatio;

    private String logradouro;

    private int numero;

    private String complemento;

    private String cep;

    private String cidade;

    private String uf;

    private String pais;

    private int lotacao;

    private List<MotoPatio> entradas;

    public long getIdPatio() {
        return idPatio;
    }

    public void setIdPatio(long idPatio) {
        this.idPatio = idPatio;
    }

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
