package hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hotel.model.Soba;
import hotel.repository.SobaRepository;
import hotel.service.SobaService;

@Service
public class JpaSobaService implements SobaService{
	
	@Autowired
	private SobaRepository sobaRepository;

	@Override
	public Soba getOne(Integer id) {
		return sobaRepository.getOne(id);
	}

	@Override
	public List<Soba> findAll() {
		return sobaRepository.findAll();
	}

	@Override
	public Page<Soba> findAll(int pageNum){
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return sobaRepository.findAll(pageable);
	}

	@Override
	public Soba save(Soba soba) {
		return sobaRepository.save(soba);
	}

	@Override
	public Soba delete(Integer id) {
		Soba soba = sobaRepository.getOne(id);
		if(soba != null) {
			sobaRepository.delete(soba);
		}
		return soba;
	}

	@Override
	public Page<Soba> search(String naziv, Integer brojKreveta, String slobodnoTekst, int pageNum) {
		if(naziv != null) {
			naziv = '%' + naziv + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return sobaRepository.search(naziv, brojKreveta, slobodnoTekst, pageable);
	}

}
