package hotel.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hotel.model.Gost;
import hotel.service.GostService;
import hotel.web.dto.GostDTO;


@Component
public class GostDTOToGost implements Converter<GostDTO, Gost>{

	@Autowired
	private GostService gostService;
	
	@Override
	public Gost convert(GostDTO gostDTO) {
	
			
			Gost gost = null;
			
			if(gostDTO.getId() != null) {
				gost = gostService.getOne(gostDTO.getId());

				if(gost == null){
					throw new IllegalStateException("Tried to "
							+ "modify a non-existant Klijent");
				}
			}
			else {
				gost = new Gost();
			}
			
			gost.setId(gostDTO.getId());
			gost.setNaziv(gostDTO.getNaziv());
			gost.setMesto(gostDTO.getMesto());
			gost.setJmbg(gostDTO.getJmbg());
			gost.setTelefon(gostDTO.getTelefon());

			return gost;


	}

	public List<Gost> convert(List<GostDTO> gostDTOs){
		List<Gost> ret = new ArrayList<>();
		
		for(GostDTO gostDTO : gostDTOs){
			ret.add(convert(gostDTO));
		}
		
		return ret;
	}

}
