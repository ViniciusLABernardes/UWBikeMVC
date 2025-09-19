package br.com.UWBike.repository;


import br.com.UWBike.model.MotoPatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MotoPatioRepository extends JpaRepository<MotoPatio,Long> {

    @Query("SELECT mp FROM MotoPatio mp WHERE mp.moto.idMoto = :idMoto AND mp.dataHoraSaida IS NULL ORDER BY mp.dataHoraEntrada DESC")
    Optional<MotoPatio> findEntradaAbertaDaMoto(@Param("idMoto") Long idMoto);
}
