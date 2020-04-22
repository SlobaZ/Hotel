package hotel.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.model.Rezervacija;

@Repository
public interface RezervacijaRepository  extends JpaRepository<Rezervacija, Integer>{
	
	@Query("SELECT r FROM Rezervacija r WHERE "
			+ "(:nazivSobe IS NULL or r.soba.naziv like :nazivSobe ) AND "
			+ "(:datumVremePocetak IS NULL or r.datumvremeUlaz >= :datumVremePocetak ) AND "
			+ "(:datumVremeKraj IS NULL or r.datumvremeIzlaz <= :datumVremeKraj ) "
			)
	Page<Rezervacija> search(
			@Param("nazivSobe") String nazivSobe, 
			@Param("datumVremePocetak") Timestamp datumVremePocetak,
			@Param("datumVremeKraj") Timestamp datumVremeKraj,
			Pageable pageRequest);

}
