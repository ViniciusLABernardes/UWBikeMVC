package br.com.UWBike.dto;


import br.com.UWBike.model.Patio;

public class AncoraResponseDto {
    public AncoraResponseDto(){

    }
    public AncoraResponseDto(long id,double x, double y,Patio patio) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.patio = patio;
    }

    private Long id;

    private double x;

    private double y;

    private Patio patio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }
}
