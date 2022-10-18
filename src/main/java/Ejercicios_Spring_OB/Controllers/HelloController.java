package Ejercicios_Spring_OB.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.message}")
    String mensaje;

    @GetMapping("/Saludo")
    public String sayHello(){
        System.out.println(mensaje);
        return "Hola, Mundo!";
    }
}
