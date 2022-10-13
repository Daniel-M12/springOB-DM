package Ejercicios_4_5_6.Controllers;

import Ejercicios_4_5_6.Entities.Laptop;
import Ejercicios_4_5_6.Repositories.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    /*@GetMapping("/api/laptops/{id}")
    public Laptop findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOptional = laptopRepository.findById(id);
        if (laptopOptional.isPresent()){
            return laptopOptional.get();
        } else {
            return null;
        }
    }*/
    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOptional = laptopRepository.findById(id);
        if (laptopOptional.isPresent()){
            return ResponseEntity.ok(laptopOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/laptops")
    public Laptop create(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }


}
