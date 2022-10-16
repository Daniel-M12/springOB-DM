package Ejercicios_Spring_OB.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/Saludo")
    public String sayHello(){
        return "Hola, Mundo!";
    }
}