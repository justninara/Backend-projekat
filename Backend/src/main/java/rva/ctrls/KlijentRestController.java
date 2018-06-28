 package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
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
import rva.rps.KlijentRepository;

@RestController
@Api(tags = {"Klijent CRUD operacije"})

public class KlijentRestController {
	@Autowired
	private KlijentRepository klijentRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("klijent")
	@ApiOperation(value = "Vrаća kolekciju svih klijenata iz baze podataka")
	public Collection<Klijent> getKlijent(){
		return klijentRepository.findAll();
	}
	
	@GetMapping("klijent/{id}")
	@ApiOperation(value = "Vrаća klijenta iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public Klijent getKlijent(@PathVariable("id") Integer id) {
		return klijentRepository.getOne(id);
	}
	
	@GetMapping("klijentIme/{ime}")
	@ApiOperation(value = "Vrаća klijenta iz baze podataka koji u ime sadrzi string prosleđen kao path varijabla")
	public Collection<Klijent> getKlijentByIme(@PathVariable("ime") String ime){
		return klijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@Transactional
	@DeleteMapping("klijent/{id}")
	@CrossOrigin
	@ApiOperation(value = "Briše klijenta iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public ResponseEntity <Klijent> deleteKlijent(@PathVariable ("id") Integer id){
		if(!klijentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		jdbcTemplate.execute("delete from racun where klijent = "+id);
		klijentRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// insert
	@PostMapping("klijent")
	@CrossOrigin
	@ApiOperation(value = "Insertuje klijenta u bazu podataka")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent){
		if(klijentRepository.existsById(klijent.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		klijentRepository.save(klijent);
		return new ResponseEntity<>(HttpStatus.OK);
	}

		
	// update
	@PutMapping("klijent")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje klijenta iz baze podataka")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent){
		if(klijentRepository.existsById(klijent.getId())) {
			klijentRepository.save(klijent);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}

		
		
}
