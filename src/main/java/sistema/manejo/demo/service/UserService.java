package sistema.manejo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sistema.manejo.demo.model.Role;
import sistema.manejo.demo.model.User;
import sistema.manejo.demo.repository.UserRepository;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	public boolean saveUser(User user) throws Exception {
		
	    if (userRepository.findByUsername(user.getUsername())!=null)
	        return false; // El usuario ya existe
	  
		userRepository.save(user);
		return true;	
	}
	
	public User getUser(String username) throws Exception {
		return userRepository.findByUsername(username);
	}
	
	public boolean deleteUser(Integer userid) throws Exception {
	    Optional<User> userOptional = userRepository.findById(userid);

	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        user.setStatus(0);  				// Marcar el user como inactivo en lugar de eliminarlo
	        userRepository.save(user);  		// Guardar los cambios en la base de datos
	        return true;  						// Eliminación lógica exitosa
	    }

	    return false;		// user no encontrado
	}
	
    public List<User> getAllUsers() throws Exception {
        return userRepository.findByStatus(1);  
    }

    
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
