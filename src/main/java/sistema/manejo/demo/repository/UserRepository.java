package sistema.manejo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.manejo.demo.model.Role;
import sistema.manejo.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
    List<User> findByStatus(int status);

}
