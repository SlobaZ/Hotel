package hotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.model.Gost;
import hotel.model.Rezervacija;

@Repository
public interface GostRepository extends JpaRepository<Gost, Integer>{
	

	@Query("SELECT g FROM Gost g WHERE "
			+ "(:naziv IS NULL or g.naziv like :naziv ) AND "
			+ "(:mesto IS NULL OR g.mesto like :mesto) "
			)
	Page<Gost> search(
			@Param("naziv") String naziv, 
			@Param("mesto") String mesto, 
			Pageable pageRequest);
	
	
	@Query("SELECT r FROM Rezervacija r WHERE r.gost.id = :idG")
	List<Rezervacija> podatakGosta( @Param("idG") Integer idG);
	
}
