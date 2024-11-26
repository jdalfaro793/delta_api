package sistema.manejo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.manejo.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
	Role findByName(String name);
    
}
