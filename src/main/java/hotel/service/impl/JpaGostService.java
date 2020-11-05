package hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hotel.model.Gost;
import hotel.model.Rezervacija;
import hotel.repository.GostRepository;
import hotel.service.GostService;


@Service
public class JpaGostService implements GostService{
	
	@Autowired
	private GostRepository gostRepository;
	
	@Override
	public Gost getOne(Integer id) {
		return gostRepository.getOne(id);
	}

	@Override
	public List<Gost> findAll() {
		return gostRepository.findAll();
	}

	@Override
	public Page<Gost> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return gostRepository.findAll(pageable);
	}

	@Override
	public Gost save(Gost gost) {
		return gostRepository.save(gost);
	}

	@Override
	public Gost delete(Integer id) {
		Gost gost = gostRepository.getOne(id);
		if(gost != null) {
			gostRepository.delete(gost);
		}
		return gost;
	}

	@Override
	public Page<Gost> search(String naziv, String mesto, int pageNum) {
		if( naziv != null) {
			naziv = '%' + naziv + '%';
		}
		if(mesto != null) {
			mesto = '%' + mesto + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return gostRepository.search(naziv, mesto, pageable);
	}

	
	@Override
	public List<Rezervacija> podatakGosta(Integer idG) {
		List<Rezervacija> rezervacije = gostRepository.podatakGosta(idG);
		return rezervacije;
	}
	
	

}
