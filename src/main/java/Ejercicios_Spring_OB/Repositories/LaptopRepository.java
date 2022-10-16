package Ejercicios_Spring_OB.Repositories;

import Ejercicios_Spring_OB.Entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long> {
}
