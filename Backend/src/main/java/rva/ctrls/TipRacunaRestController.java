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


import rva.jpa.TipRacuna;

import rva.rps.TipracunaRepository;

@RestController


public class TipRacunaRestController {
	
	@Autowired
	private TipracunaRepository tipRacunaRepository;

	@GetMapping("TipRacuna")
	
	public Collection<TipRacuna> getTipRacuna(){
		return tipRacunaRepository.findAll();
	}
	
	@GetMapping("TipRacuna/{id}")
	
	public TipRacuna getTipRacuna(@PathVariable("id") Integer id) {
		return tipRacunaRepository.getOne(id);
	}
	
	@GetMapping("TipRacunaNaziv/{naziv}")
	
	public Collection<TipRacuna> getTipRacunaByNaziv(@PathVariable("naziv") String naziv){
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("TipRacuna/{id}")
	
	public ResponseEntity<TipRacuna> deleteTipRacuna(@PathVariable ("id") Integer id){
		if(!tipRacunaRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		tipRacunaRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// insert
	@PostMapping("TipRacuna")
	
	public ResponseEntity<TipRacuna> insertTipRacuna(@RequestBody TipRacuna tipRacuna){
		if(tipRacunaRepository.existsById(tipRacuna.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// update
	@PutMapping("TipRacuna")
	
	public ResponseEntity<TipRacuna> updateTipRacuna(@RequestBody TipRacuna tipRacuna){
		if(tipRacunaRepository.existsById(tipRacuna.getId())) {
			tipRacunaRepository.save(tipRacuna);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	
	
	

}
