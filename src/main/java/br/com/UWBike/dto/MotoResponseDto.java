package br.com.UWBike.dto;



import br.com.UWBike.model.MotoPatio;

import java.util.List;

public class MotoResponseDto {

    public MotoResponseDto(){

    }

    public MotoResponseDto(long idMoto,String modelo, String placa, String chassi) {
        this.idMoto = idMoto;
        this.modelo = modelo;
        this.placa = placa;
        this.chassi = chassi;
    }

    private long idMoto;

    private String modelo;

    private String placa;

    private String chassi;

    private List<MotoPatio> historicoEntrada;

    public long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(long idMoto) {
        this.idMoto = idMoto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public List<MotoPatio> getHistoricoEntrada() {
        return historicoEntrada;
    }

    public void setHistoricoEntrada(List<MotoPatio> historicoEntrada) {
        this.historicoEntrada = historicoEntrada;
    }
}
