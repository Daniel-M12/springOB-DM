package Ejercicios_4_5_6.Repositories;

import Ejercicios_4_5_6.Entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long> {
}
