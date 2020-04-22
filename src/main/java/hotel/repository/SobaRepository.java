package hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.model.Soba;

@Repository
public interface SobaRepository extends JpaRepository<Soba, Integer>{

	@Query("SELECT s FROM Soba s WHERE "
			+ "(:naziv IS NULL or s.naziv like :naziv ) AND "
			+ "(:brojKreveta IS NULL or s.brojKreveta = :brojKreveta ) AND "
			+ "(:slobodnoTekst IS NULL OR s.slobodnoTekst = :slobodnoTekst) "
			)
	Page<Soba> search(
			@Param("naziv") String naziv, 
			@Param("brojKreveta") Integer brojKreveta, 
			@Param("slobodnoTekst") String slobodnoTekst,
			Pageable pageRequest);
	
	

}
