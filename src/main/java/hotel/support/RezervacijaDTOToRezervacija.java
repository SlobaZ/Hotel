package hotel.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hotel.utils.PomocnaKlasa;
import hotel.model.Gost;
import hotel.model.Rezervacija;
import hotel.model.Soba;
import hotel.service.GostService;
import hotel.service.RezervacijaService;
import hotel.service.SobaService;
import hotel.web.dto.RezervacijaDTO;


@Component
public class RezervacijaDTOToRezervacija implements Converter<RezervacijaDTO, Rezervacija>{

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private GostService gostService;
	
	@Autowired
	private SobaService sobaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO rezervacijaDTO) {
		
		Gost gost = gostService.getOne(rezervacijaDTO.getGostId());
		Soba soba = sobaService.getOne(rezervacijaDTO.getSobaId());
		
		if(gost!=null && soba!=null) {
			
			Rezervacija rezervacija = null;
			
			if(rezervacijaDTO.getId() != null) {
				rezervacija = rezervacijaService.getOne(rezervacijaDTO.getId());
			}
			else {
				rezervacija = new Rezervacija();
			}
			
			rezervacija.setId(rezervacijaDTO.getId());
			rezervacija.setDatumvremeUlaz(PomocnaKlasa.KonvertujStringUSqlDatumIVreme(rezervacijaDTO.getDatetimeUlaz()));
			rezervacija.setDatetimeUlaz(rezervacijaDTO.getDatetimeUlaz());
			rezervacija.setDatumvremeIzlaz(PomocnaKlasa.KonvertujStringUSqlDatumIVreme(rezervacijaDTO.getDatetimeIzlaz()));
			rezervacija.setDatetimeIzlaz(rezervacijaDTO.getDatetimeIzlaz());
			
			rezervacija.setGost(gost);
			rezervacija.setSoba(soba);
			
			return rezervacija;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Rezervacija> convert(List<RezervacijaDTO> rezervacijaDTOs){
		List<Rezervacija> ret = new ArrayList<>();
		
		for(RezervacijaDTO rezervacijaDTO : rezervacijaDTOs){
			ret.add(convert(rezervacijaDTO));
		}
		
		return ret;
	}
}
