package hotel.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hotel.model.Soba;
import hotel.web.dto.SobaDTO;

@Component
public class SobaToSobaDTO implements Converter<Soba, SobaDTO>{

	@Override
	public SobaDTO convert(Soba soba) {
		
		if(soba==null){
			return null;
		}
		
		SobaDTO retValue = new SobaDTO();
		
		retValue.setId(soba.getId());
		retValue.setNaziv(soba.getNaziv());
		retValue.setBrojKreveta(soba.getBrojKreveta());
		retValue.setSlobodno(soba.isSlobodno());
		retValue.setSlobodnoTekst(soba.getSlobodnoTekst());
		
		if(soba.getGost()!=null) {
		retValue.setGostId(soba.getGost().getId());
		retValue.setGostNaziv(soba.getGost().getNaziv());
		}
		else {
			retValue.setGostId(0);
			retValue.setGostNaziv("");
		}
		
		return retValue;
	}

	public List<SobaDTO> convert(List<Soba> sobe){
		List<SobaDTO> ret = new ArrayList<>();
		
		for(Soba soba : sobe){
			ret.add(convert(soba));
		}
		
		return ret;
	}


}
