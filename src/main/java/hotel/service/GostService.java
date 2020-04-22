package hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import hotel.model.Gost;
import hotel.model.Rezervacija;

public interface GostService {
	
	Gost getOne(Integer id);
	List<Gost> findAll();
	Page<Gost> findAll(int pageNum);
	Gost save(Gost gost);
	Gost delete(Integer id);
	
	Page<Gost> search(
			@Param("naziv") String naziv, 
			@Param("mesto") String mesto, 
			 int pageNum);
	
	Rezervacija podatakGosta(Integer idG);

}
