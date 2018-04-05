package rva.rps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.TipRacuna;

public interface TipracunaRepository extends JpaRepository<TipRacuna, Integer>{
	Collection<TipRacuna> findByNazivContainingIgnoreCase(String naziv);

}
