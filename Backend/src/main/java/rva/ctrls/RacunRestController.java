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
import rva.jpa.Klijent;
import rva.jpa.Racun;
import rva.rps.KlijentRepository;
import rva.rps.RacunRepository;

@RestController
@Api(tags = {"Racun CRUD operacije"})


public class RacunRestController {
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired 
	private KlijentRepository klijentRepository;

	@GetMapping("racun")
	@ApiOperation(value = "Vrаća kolekciju svih racuna iz baze podataka")
	public Collection<Racun> getRacun(){
		return racunRepository.findAll();
	}
	
	@GetMapping(value = "racun/{id}")
	@ApiOperation(value = "Vraća racun iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Racun> getRacun(@PathVariable ("id") Integer id) {
		Racun racun = racunRepository.getOne(id);
		return new ResponseEntity<Racun> (racun, HttpStatus.OK);
	}
	/*@GetMapping("racun/{id}")
	@ApiOperation(value = "Vrаća racun iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public Racun getRacun(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}*/
	
	@GetMapping(value = "racuniZaKlijent/{id}")
	@ApiOperation(value = "Vraća sve racune iz baze podataka vezane za klijenta čiji je id vrednost prosleđena kao path varijabla")
	public Collection<Racun> racunZaKlijentId(@PathVariable("id") Integer id){
		Klijent k = klijentRepository.getOne(id);
		return racunRepository.findByKlijent(k);
	}
	
	
	@GetMapping(value = "racunNaziv/{naziv}")
	@ApiOperation(value = "Vrаća racun iz baze podataka koji u naziv sadrzi string prosleđen kao path varijabla")
	public Collection<Racun> getRacunByNaziv(@PathVariable("naziv") String naziv){
		return racunRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping(value = "racun/{id}")
	@CrossOrigin
	@ApiOperation(value = "Briše racun iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public ResponseEntity<Racun> deleteRacun(@PathVariable ("id") Integer id){
		if(!racunRepository.existsById(id))
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		racunRepository.deleteById(id);
		return new ResponseEntity<Racun> (HttpStatus.OK);
	}
	
	//insert
	/*@PostMapping(value = "racun")
	@CrossOrigin
	@ApiOperation(value = "Insertuje racun u bazu podataka")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		racunRepository.save(racun);
		return new ResponseEntity<>(HttpStatus.OK);
	}*/
	
	@PostMapping (value="racun")
	@ApiOperation(value = "Upisuje racun u bazu podataka")
	public ResponseEntity<Void> insertRacun (@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId()))
			return new ResponseEntity<Void> (HttpStatus.CONFLICT);
		
		racunRepository.save(racun);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	
	//update
	
	@PutMapping (value = "racun")
	@ApiOperation(value = "Modifikuje postojeći racun u bazi podataka")
	public ResponseEntity<Void> updateRacun (@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId()))
			return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
		racunRepository.save(racun);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	/*@PutMapping(value = "racun")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje racun iz baze podataka")
	public ResponseEntity<Void> updateRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<Void> (HttpStatus.OK);
		}
		return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
