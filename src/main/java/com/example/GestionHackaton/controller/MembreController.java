package com.example.GestionHackaton.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.GestionHackaton.model.Membre;
import com.example.GestionHackaton.repository.MembreRepository;

@RestController
@RequestMapping("membres")
@CrossOrigin(origins="http://localhost:4200")
public class MembreController {
	
	@Autowired
	MembreRepository memrepository;
	
	@PostMapping("/save")
	public Membre save(@RequestBody Membre event){
		return memrepository.save(event);
	}
	@GetMapping("/all")
	public List<Membre> load(){
		return memrepository.findAll();
	}

}
