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
import rva.jpa.Kredit;
import rva.rps.KreditRepository;

@RestController
@Api(tags = {"Kredit CRUD operacije"})
public class KreditRestController {
	
	@Autowired
	private KreditRepository kreditRepository;

	@GetMapping("kredit")
	@ApiOperation(value = "Vrаća kolekciju svih kredita iz baze podataka")
	public Collection<Kredit> getKredit(){
		return kreditRepository.findAll();
	}
	
	@GetMapping("kredit/{id}")
	@ApiOperation(value = "Vrаća kredit iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public Kredit getKredit(@PathVariable("id") Integer id) {
		return kreditRepository.getOne(id);
	}
	
	@GetMapping("kreditNaziv/{naziv}")
	@ApiOperation(value = "Vrаća kredit iz baze podataka koji u naziv sadrzi string prosleđen kao path varijabla")
	public Collection<Kredit> getKreditByNaziv(@PathVariable("naziv") String naziv){
		return kreditRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("kredit/{id}")
	@CrossOrigin
	@ApiOperation(value = "Briše kredit iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public ResponseEntity<Kredit> deleteKredit(@PathVariable ("id") Integer id){
		if(!kreditRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		kreditRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	
	// insert
	@PostMapping("kredit")
	@CrossOrigin
	@ApiOperation(value = "Insertuje kredit u bazu podataka")
	public ResponseEntity<Kredit> insertKredit(@RequestBody Kredit kredit){
		if(kreditRepository.existsById(kredit.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		kreditRepository.save(kredit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	// update
	@PutMapping("kredit")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje kredit iz baze podataka")
	public ResponseEntity<Kredit> updateKredit(@RequestBody Kredit kredit){
		if(kreditRepository.existsById(kredit.getId())) {
			kreditRepository.save(kredit);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
		
		
		

}
