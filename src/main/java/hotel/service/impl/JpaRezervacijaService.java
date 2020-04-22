package hotel.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hotel.model.Gost;
import hotel.model.Rezervacija;
import hotel.model.Soba;
import hotel.repository.GostRepository;
import hotel.repository.RezervacijaRepository;
import hotel.repository.SobaRepository;
import hotel.service.RezervacijaService;
import hotel.utils.PomocnaKlasa;

@Service
public class JpaRezervacijaService implements RezervacijaService{
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private SobaRepository sobaRepository;
	
	@Autowired
	private GostRepository gostRepository;
	

	@Override
	public Rezervacija getOne(Integer id) {
		return rezervacijaRepository.getOne(id);
	}

	@Override
	public List<Rezervacija> findAll() {
		return rezervacijaRepository.findAll();
	}

	@Override
	public Page<Rezervacija> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return rezervacijaRepository.findAll(pageable);
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		Soba soba = rezervacija.getSoba();
		Gost gost = rezervacija.getGost();
		soba.setSlobodno(false);
		soba.setSlobodnoTekst("NE");
		sobaRepository.save(soba);
		gostRepository.save(gost);	
		return rezervacijaRepository.save(rezervacija);
	}

	@Override
	public Rezervacija delete(Integer id) {
		Rezervacija rezervacija = rezervacijaRepository.getOne(id);
		if(rezervacija != null) {
			rezervacijaRepository.delete(rezervacija);
		}
		return rezervacija;
	}

	@Override
	public Page<Rezervacija> search(String nazivSobe, String datumvremePocetak, String datumvremeKraj, int pageNum) {
		Timestamp datumVremePocetak = null;
		Timestamp datumVremeKraj = null;
		
		if(nazivSobe != null) {
			nazivSobe = '%' + nazivSobe + '%';
		}

		if(datumvremePocetak != null) { 
		datumVremePocetak = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremePocetak);
		}
		if(datumvremeKraj !=null) {
			 datumVremeKraj = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremeKraj);
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return rezervacijaRepository.search(nazivSobe, datumVremePocetak, datumVremeKraj, pageable);
	}

}
