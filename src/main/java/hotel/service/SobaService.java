package hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import hotel.model.Soba;

public interface SobaService {
	
	Soba getOne(Integer id);
	List<Soba> findAll();
	Page<Soba> findAll(int pageNum);
	Soba save(Soba soba);
	Soba delete(Integer id);
	
	Page<Soba> search(
			@Param("naziv") String naziv, 
			@Param("brojKreveta") Integer brojKreveta, 
			@Param("slobodnoTekst") String slobodnoTekst,
			 int pageNum);

}
