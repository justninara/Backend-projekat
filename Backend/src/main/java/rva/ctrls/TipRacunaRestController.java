package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.TipRacuna;
import rva.rps.TipracunaRepository;

@RestController
@Api(tags = {"TipRacuna CRUD operacije"})

public class TipRacunaRestController {
	
	@Autowired
	private TipracunaRepository tipRacunaRepository;

	@GetMapping("tipRacuna")
	@ApiOperation(value = "Vrаća kolekciju svih tipova racuna iz baze podataka")
	public Collection<TipRacuna> getTipRacuna(){
		return tipRacunaRepository.findAll();
	}
	
	@GetMapping("tipRacuna/{id}")
	@ApiOperation(value = "Vrаća tip racuna iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public TipRacuna getTipRacuna(@PathVariable("id") Integer id) {
		return tipRacunaRepository.getOne(id);
	}
	
	@GetMapping("tipRacunaNaziv/{naziv}")
	@ApiOperation(value = "Vrаća tip racuna iz baze podataka koji u naziv sadrzi string prosleđen kao path varijabla")
	public Collection<TipRacuna> getTipRacunaByNaziv(@PathVariable("naziv") String naziv){
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("tipRacuna/{id}")
	@ApiOperation(value = "Briše tip racuna iz baze podataka ciji je ID vrednost prosleđena kao path varijabla")
	public ResponseEntity<TipRacuna> deleteTipRacuna(@PathVariable ("id") Integer id){
		if(!tipRacunaRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		tipRacunaRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// insert
	@PostMapping("tipRacuna")
	@ApiOperation(value = "Insertuje tip racuna u bazu podataka")
	public ResponseEntity<TipRacuna> insertTipRacuna(@RequestBody TipRacuna tipRacuna){
		if(tipRacunaRepository.existsById(tipRacuna.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// update
	@PutMapping("tipRacuna")
	@ApiOperation(value = "Modifikuje tip racuna iz baze podataka")
	public ResponseEntity<TipRacuna> updateTipRacuna(@RequestBody TipRacuna tipRacuna){
		if(tipRacunaRepository.existsById(tipRacuna.getId())) {
			tipRacunaRepository.save(tipRacuna);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	
	
	

}
