package hotel.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hotel.model.SobaCenovnik;

@RestController
@RequestMapping(value="/cenovniksobe")
public class ApiSobaCenovnikController {
	
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SobaCenovnik>> SpisakSoba(){
		
		List<SobaCenovnik> listasoba = SobaCenovnik.cene();
		
		return new ResponseEntity<>( listasoba , HttpStatus.OK);
	}
	
	

}
