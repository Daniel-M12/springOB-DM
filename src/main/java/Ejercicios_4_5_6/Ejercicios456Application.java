package Ejercicios_4_5_6;

import Ejercicios_4_5_6.Entities.Laptop;
import Ejercicios_4_5_6.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicios456Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ejercicios456Application.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		laptopRepository.save(new Laptop(null,"Acer","AL-2413",2015));
		laptopRepository.save(new Laptop(null,"MacBook","Air",2021));

		System.out.println(laptopRepository.findAll());
	}

}
