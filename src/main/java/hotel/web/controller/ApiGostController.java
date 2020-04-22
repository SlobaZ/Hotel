package hotel.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotel.model.Gost;
import hotel.model.Rezervacija;
import hotel.service.GostService;
import hotel.support.GostDTOToGost;
import hotel.support.GostToGostDTO;
import hotel.utils.PomocnaKlasa;
import hotel.web.dto.GostDTO;


@RestController
@RequestMapping(value="/gosti")
public class ApiGostController {

	@Autowired
	private GostService gostService;
	
	@Autowired
	private GostToGostDTO toDTO;
	 
	@Autowired
	private GostDTOToGost toGost;
		
	
	
	@RequestMapping(value="/sve", method=RequestMethod.GET)
	ResponseEntity<List<GostDTO>> getAlls() {
		List<Gost> gostPage = null;
		gostPage = gostService.findAll();
	return new ResponseEntity<>( toDTO.convert(gostPage) , HttpStatus.OK);
	}	

	

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<GostDTO>> getAll(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String mesto,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Gost> gostPage = null;
		
		if(naziv != null || mesto != null ) {
			gostPage = gostService.search(naziv, mesto, pageNum);
		}
		else {
			gostPage = gostService.findAll(pageNum);
		}
	
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(gostPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(gostPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<GostDTO> getOne(@PathVariable Integer id){
		Gost gost = gostService.getOne(id);
		if(gost==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(gost), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<GostDTO> delete(@PathVariable Integer id){
		Gost deleted = gostService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<GostDTO> add( @Validated @RequestBody GostDTO newgostDTO){
		
		Gost savedgost = gostService.save(toGost.convert(newgostDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedgost), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<GostDTO> edit(
			@Validated @RequestBody GostDTO gostDTO,
			@PathVariable Integer id){
		
		if(!id.equals(gostDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Gost persisted = gostService.save(toGost.convert(gostDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(value="/{idG}/podatakGosta", method=RequestMethod.GET)
	ResponseEntity<?> podatakGosta(@PathVariable Integer idG){
		Rezervacija rezervacija = gostService.podatakGosta(idG);
		if(rezervacija==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Integer idGosta = rezervacija.getGost().getId();
		String gostId = String.valueOf (idGosta) ; 
		String gostNaziv = rezervacija.getGost().getNaziv();
		Integer idSobe = rezervacija.getSoba().getId();
		String sobaId = String.valueOf (idSobe) ; 
		String sobaNaziv = rezervacija.getSoba().getNaziv();
		String ulaz = rezervacija.getDatetimeUlaz();
		String izlaz = rezervacija.getDatetimeIzlaz();
		
		Double brojDanaD = PomocnaKlasa.BrojDana(ulaz,izlaz);
		Double cenaD = PomocnaKlasa.cena(brojDanaD, sobaNaziv);
		
		String brojDana = String.valueOf ( brojDanaD ) ; 
		String cena = String.valueOf ( cenaD ) ; 

		List<String> podatak = new ArrayList<String>();
		podatak.add(gostId);
		podatak.add(gostNaziv);
		podatak.add(sobaId);
		podatak.add(sobaNaziv);
		podatak.add(ulaz);
		podatak.add(izlaz);
		podatak.add(brojDana);
		podatak.add(cena);
		return new ResponseEntity<List<String>>( podatak , HttpStatus.OK );
	}
	


	
	
}
