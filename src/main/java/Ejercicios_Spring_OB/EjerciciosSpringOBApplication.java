package Ejercicios_Spring_OB;

import Ejercicios_Spring_OB.Entities.Laptop;
import Ejercicios_Spring_OB.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EjerciciosSpringOBApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EjerciciosSpringOBApplication.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		laptopRepository.save(new Laptop(null,"Acer","AL-2413",2015));
		laptopRepository.save(new Laptop(null,"MacBook","Air",2021));

		System.out.println(laptopRepository.findAll());
	}

}
