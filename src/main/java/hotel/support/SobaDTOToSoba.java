package hotel.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hotel.model.Gost;
import hotel.model.Soba;
import hotel.service.GostService;
import hotel.service.SobaService;
import hotel.web.dto.SobaDTO;

@Component
public class SobaDTOToSoba implements Converter<SobaDTO, Soba>{

	@Autowired
	private SobaService sobaService;
	
	@Autowired
	private GostService gostService;
	
	
	@Override
	public Soba convert(SobaDTO sobaDTO) {
		
		Gost gost = gostService.getOne(sobaDTO.getGostId());
				
			Soba soba = null;
			
			if(sobaDTO.getId() != null) {
				soba = sobaService.getOne(sobaDTO.getId());
			}
			else {
				soba = new Soba();
			}
			
			soba.setId(sobaDTO.getId());
			soba.setNaziv(sobaDTO.getNaziv());
			soba.setBrojKreveta(sobaDTO.getBrojKreveta());
			soba.setBrojKreveta(sobaDTO.getBrojKreveta());
			soba.setSlobodno(sobaDTO.isSlobodno());
			soba.setSlobodnoTekst(sobaDTO.getSlobodnoTekst());
			
			if(gost!=null) {
			soba.setGost(gost);
			}
			
			return soba;
		}
		



	public List<Soba> convert(List<SobaDTO> SobaDTOs){
		List<Soba> ret = new ArrayList<>();
		
		for(SobaDTO SobaDTO : SobaDTOs){
			ret.add(convert(SobaDTO));
		}
		
		return ret;
	}

}
