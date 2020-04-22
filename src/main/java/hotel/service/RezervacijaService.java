package hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import hotel.model.Rezervacija;


public interface RezervacijaService {
	
	Rezervacija getOne(Integer id);
	List<Rezervacija> findAll();
	Page<Rezervacija> findAll(int pageNum);
	Rezervacija save(Rezervacija rezervacija);
	Rezervacija delete(Integer id);
	
	Page<Rezervacija> search(
			@Param("nazivSobe") String nazivSobe, 
			@Param("datumvremePocetak") String datumvremePocetak,
			@Param("datumvremeKraj") String datumvremeKraj,
			 int pageNum);
	

}
