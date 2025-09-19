package br.com.UWBike.dto;

import jakarta.validation.constraints.NotNull;

public class MotoPatioRequestDto {
    public MotoPatioRequestDto(){

    }
    public MotoPatioRequestDto(Long idMoto,Long idPatio){
        this.idMoto = idMoto;
        this.idPatio = idPatio;
    }

    @NotNull(message = "O id da moto é obrigatório")
    private Long idMoto;
    @NotNull(message = "O id do pátio é obrigatório")
    private Long idPatio;




    public Long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    public Long getIdPatio() {
        return idPatio;
    }

    public void setIdPatio(Long idPatio) {
        this.idPatio = idPatio;
    }


}
