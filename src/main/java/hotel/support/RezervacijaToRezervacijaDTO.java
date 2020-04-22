package hotel.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hotel.utils.PomocnaKlasa;
import hotel.model.Rezervacija;
import hotel.web.dto.RezervacijaDTO;


@Component
public class RezervacijaToRezervacijaDTO implements Converter<Rezervacija, RezervacijaDTO>{

	@Override
	public RezervacijaDTO convert(Rezervacija rezervacija) {
		
		if(rezervacija==null){
			return null;
		}
				
		RezervacijaDTO retValue = new RezervacijaDTO();
		
		retValue.setId(rezervacija.getId());
		retValue.setDatumvremeUlaz(rezervacija.getDatumvremeUlaz());
		retValue.setDatetimeUlaz(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(rezervacija.getDatumvremeUlaz()));
		retValue.setDatumvremeIzlaz(rezervacija.getDatumvremeIzlaz());
		retValue.setDatetimeIzlaz(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(rezervacija.getDatumvremeIzlaz()));
		
		retValue.setGostId(rezervacija.getGost().getId());
		retValue.setSobaId(rezervacija.getSoba().getId());
		
		retValue.setGostNaziv(rezervacija.getGost().getNaziv());
		retValue.setGostMesto(rezervacija.getGost().getMesto());
		retValue.setGostJmbg(rezervacija.getGost().getJmbg());
		retValue.setGostTelefon(rezervacija.getGost().getTelefon());
		
		retValue.setSobaNaziv(rezervacija.getSoba().getNaziv());
		retValue.setSobaBrojKreveta(rezervacija.getSoba().getBrojKreveta());
		retValue.setSobaSlobodno(rezervacija.getSoba().isSlobodno());
		retValue.setSobaSlobodnoTekst(rezervacija.getSoba().getSlobodnoTekst());
		
		return retValue;
	}

	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
		List<RezervacijaDTO> ret = new ArrayList<>();
		
		for(Rezervacija rezervacija : rezervacije){
			ret.add(convert(rezervacija));
		}
		
		return ret;
	}

}
