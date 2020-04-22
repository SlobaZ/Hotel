package hotel.web.controller;

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
import hotel.model.Soba;
import hotel.service.GostService;
import hotel.service.RezervacijaService;
import hotel.service.SobaService;
import hotel.support.RezervacijaDTOToRezervacija;
import hotel.support.RezervacijaToRezervacijaDTO;
import hotel.web.dto.RezervacijaDTO;


@RestController
@RequestMapping(value="/rezervacije")
public class ApiRezervacijaController {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaToRezervacijaDTO toDTO;
	 
	@Autowired
	private RezervacijaDTOToRezervacija toRezervacija;
	
	@Autowired
	private SobaService sobaService;
	
	@Autowired
	private GostService gostService;
		

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<RezervacijaDTO>> getAll(
			@RequestParam(required=false) String nazivSobe,
			@RequestParam(required=false) String datumvremePocetak,
			@RequestParam(required=false) String datumvremeKraj,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Rezervacija> rezervacijaPage = null;
		
		if(nazivSobe != null || datumvremePocetak != null || datumvremeKraj != null) {
			rezervacijaPage = rezervacijaService.search(nazivSobe, datumvremePocetak, datumvremeKraj, pageNum);
		}
		else {
			rezervacijaPage = rezervacijaService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(rezervacijaPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(rezervacijaPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<RezervacijaDTO> getOne(@PathVariable Integer id){
		Rezervacija rezervacija = rezervacijaService.getOne(id);
		if(rezervacija==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(rezervacija), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<RezervacijaDTO> delete(@PathVariable Integer id){
		Rezervacija deleted = rezervacijaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<RezervacijaDTO> add( @Validated @RequestBody RezervacijaDTO newrezervacijaDTO){
		if(newrezervacijaDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Soba soba = sobaService.getOne(newrezervacijaDTO.getSobaId()) ;
		Gost gost = gostService.getOne(newrezervacijaDTO.getGostId());
		
		soba.setSlobodno(false);
		
		sobaService.save(soba);
		gostService.save(gost);
		
		Rezervacija savedrezervacija = rezervacijaService.save(toRezervacija.convert(newrezervacijaDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedrezervacija), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<RezervacijaDTO> edit(
			@Validated @RequestBody RezervacijaDTO rezervacijaDTO,
			@PathVariable Integer id){
		
		if(!id.equals(rezervacijaDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Rezervacija persisted = rezervacijaService.save(toRezervacija.convert(rezervacijaDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	
	
	
}
