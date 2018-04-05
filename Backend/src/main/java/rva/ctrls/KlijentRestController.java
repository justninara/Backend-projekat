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



import rva.jpa.Klijent;
import rva.rps.KlijentRepository;

@RestController


public class KlijentRestController {
	@Autowired
	private KlijentRepository klijentRepository;

	@GetMapping("klijent")
	
	public Collection<Klijent> getKlijent(){
		return klijentRepository.findAll();
	}
	
	@GetMapping("klijent/{id}")
	
	public Klijent getKlijent(@PathVariable("id") Integer id) {
		return klijentRepository.getOne(id);
	}
	
	@GetMapping("KlijentIme/{ime}")

	public Collection<Klijent> getKlijentByIme(@PathVariable("ime") String ime){
		return klijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@DeleteMapping("Klijent/{id}")
	
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable ("id") Integer id){
		if(!klijentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		klijentRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// insert
		@PostMapping("klijent")
		
		public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent){
			if(klijentRepository.existsById(klijent.getId())) {
				return new ResponseEntity<> (HttpStatus.CONFLICT);
			}
			klijentRepository.save(klijent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		// update
		@PutMapping("klijent")
		
		public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent){
			if(klijentRepository.existsById(klijent.getId())) {
				klijentRepository.save(klijent);
				return new ResponseEntity<> (HttpStatus.OK);
			}
			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		}

		
		
}
