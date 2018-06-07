package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Racun;
import rva.rps.RacunRepository;

@RestController
@Api(tags = {"Racun CRUD operacije"})


public class RacunRestController {
	@Autowired
	private RacunRepository racunRepository;

	@GetMapping("racun")
	@ApiOperation(value = "Vrаća kolekciju svih racuna iz baze podataka")
	public Collection<Racun> getRacun(){
		return racunRepository.findAll();
	}
	
	@GetMapping("racun/{id}")
	@ApiOperation(value = "Vrаća racun iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public Racun getRacun(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}
	
	@GetMapping("racunNaziv/{naziv}")
	@ApiOperation(value = "Vrаća racun iz baze podataka koji u naziv sadrzi string prosleđen kao path varijabla")
	public Collection<Racun> getRacunByNaziv(@PathVariable("naziv") String naziv){
		return racunRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("racun/{id}")
	@CrossOrigin
	@ApiOperation(value = "Briše racun iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public ResponseEntity<Racun> deleteRacun(@PathVariable ("id") Integer id){
		if(!racunRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		racunRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	//insert
	@PostMapping("racun")
	@CrossOrigin
	@ApiOperation(value = "Insertuje racun u bazu podataka")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		racunRepository.save(racun);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//update
	@PutMapping("racun")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje racun iz baze podataka")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
