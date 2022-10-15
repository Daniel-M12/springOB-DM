package Ejercicios_4_5_6.Controllers;

import Ejercicios_4_5_6.Entities.Laptop;
import Ejercicios_4_5_6.Repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    @ApiOperation("Método para obtener un listado de todas las laptos")
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
    @ApiOperation("Método para mostrar los datos de una laptop")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Id de la laptop a buscar") @PathVariable Long id){
        Optional<Laptop> laptopOptional = laptopRepository.findById(id);
        if (laptopOptional.isPresent()){
            return ResponseEntity.ok(laptopOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/laptops")
    @ApiOperation("Método para ingresar una nueva laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            return ResponseEntity.ok(laptopRepository.save(laptop));
        } else {
         return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("api/laptops")
    @ApiOperation("Método para actualizar una laptop existente")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.ok(laptopRepository.save(laptop));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Método para borrar una laptop existente")
    public ResponseEntity<Laptop> delete (@ApiParam("Id de la laptop a eliminar") @PathVariable Long id){
        if (laptopRepository.existsById(id)){
            laptopRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiIgnore
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
