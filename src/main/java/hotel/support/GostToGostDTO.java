package hotel.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hotel.model.Gost;
import hotel.web.dto.GostDTO;


@Component
public class GostToGostDTO implements Converter<Gost, GostDTO> {

	@Override
	public GostDTO convert(Gost gost) {
		if(gost==null){
			return null;
		}
		
		GostDTO dto = new GostDTO();
		
		dto.setId(gost.getId());
		dto.setNaziv(gost.getNaziv());
		dto.setMesto(gost.getMesto());
		dto.setJmbg(gost.getJmbg());
		dto.setTelefon(gost.getTelefon());
		
		return dto;
	}
	
	public List<GostDTO> convert(List<Gost> gosti){
		List<GostDTO> ret = new ArrayList<>();
		
		for(Gost g: gosti){
			ret.add(convert(g));
		}
		
		return ret;
	}

}
