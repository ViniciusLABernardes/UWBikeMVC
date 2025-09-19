package br.com.UWBike.dto;



import br.com.UWBike.model.Moto;
import br.com.UWBike.model.Patio;

import java.time.LocalDateTime;

public class MotoPatioResponseDto {

        private Long id;

        private Moto moto;

        private Patio patio;

        private LocalDateTime dataHoraEntrada;

        private LocalDateTime dataHoraSaida;

        public MotoPatioResponseDto(){

        }

    public MotoPatioResponseDto(Long id, Moto moto, Patio patio, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida ) {
            this.id = id;
            this.moto = moto;
            this.patio = patio;
            this.dataHoraEntrada = dataHoraEntrada;
            this.dataHoraSaida = dataHoraSaida;
        }



        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Moto getMoto() {
            return moto;
        }

        public void setMoto(Moto moto) {
            this.moto = moto;
        }

        public Patio getPatio() {
            return patio;
        }

        public void setPatio(Patio patio) {
            this.patio = patio;
        }

        public LocalDateTime getDataHoraEntrada() {
            return dataHoraEntrada;
        }

        public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
            this.dataHoraEntrada = dataHoraEntrada;
        }

        public LocalDateTime getDataHoraSaida() {
            return dataHoraSaida;
        }

        public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
            this.dataHoraSaida = dataHoraSaida;
        }

}

