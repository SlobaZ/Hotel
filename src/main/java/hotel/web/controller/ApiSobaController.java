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

import hotel.model.Soba;
import hotel.service.SobaService;
import hotel.support.SobaDTOToSoba;
import hotel.support.SobaToSobaDTO;
import hotel.web.dto.SobaDTO;


@RestController
@RequestMapping(value="/sobe")
public class ApiSobaController {

	@Autowired
	private SobaService sobaService;
	
	@Autowired
	private SobaToSobaDTO toDTO;
	 
	@Autowired
	private SobaDTOToSoba toSoba;
	
	
	@RequestMapping(value="/sve", method=RequestMethod.GET)
	ResponseEntity<List<SobaDTO>> getAlls() {
		List<Soba> sobaPage = null;
		sobaPage = sobaService.findAll();
	return new ResponseEntity<>( toDTO.convert(sobaPage) , HttpStatus.OK);
	}	

	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SobaDTO>> getAll(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Integer brojKreveta,
			@RequestParam(required=false) String slobodnoTekst,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Soba> sobaPage = null;

		if(naziv != null || brojKreveta != null || slobodnoTekst != null  ) {
			sobaPage = sobaService.search(naziv, brojKreveta, slobodnoTekst, pageNum);
		}
		else {
			sobaPage = sobaService.findAll(pageNum);
		}

		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(sobaPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(sobaPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<SobaDTO> getOne(@PathVariable Integer id){
		Soba soba = sobaService.getOne(id);
		if(soba==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(soba), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<SobaDTO> delete(@PathVariable Integer id){
		Soba deleted = sobaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<SobaDTO> add( @Validated @RequestBody SobaDTO newsobaDTO){
		
		Soba savedsoba = sobaService.save(toSoba.convert(newsobaDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedsoba), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<SobaDTO> edit(
			@Validated @RequestBody SobaDTO sobaDTO,
			@PathVariable Integer id){
		
		if(!id.equals(sobaDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Soba persisted = sobaService.save(toSoba.convert(sobaDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

	
	
	
}
