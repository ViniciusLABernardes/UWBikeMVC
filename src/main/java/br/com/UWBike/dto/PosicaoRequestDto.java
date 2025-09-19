package br.com.UWBike.dto;

import jakarta.validation.constraints.NotNull;

public class PosicaoRequestDto {

        @NotNull(message = "id da moto não pode ser nulo")
        private long idMoto;
        @NotNull(message = "id do pátio não pode ser nulo")
        private long idPatio;
        private double d1;
        private double d2;
        private double d3;


        public long getIdMoto() { return idMoto; }
        public void setIdMoto(long idMoto) { this.idMoto = idMoto; }

        public long getIdPatio() { return idPatio; }
        public void setIdPatio(long idPatio) { this.idPatio = idPatio; }

        public double getD1() { return d1; }
        public void setD1(double d1) { this.d1 = d1; }

        public double getD2() { return d2; }
        public void setD2(double d2) { this.d2 = d2; }

        public double getD3() { return d3; }
        public void setD3(double d3) { this.d3 = d3; }



}
