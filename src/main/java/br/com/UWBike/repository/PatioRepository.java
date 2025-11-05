package br.com.UWBike.repository;


import br.com.UWBike.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<Patio,Long> {
}
