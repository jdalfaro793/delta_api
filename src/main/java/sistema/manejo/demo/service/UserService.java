package sistema.manejo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.manejo.demo.model.User;
import sistema.manejo.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public boolean saveUser(User user) throws Exception {
		
	    if (userRepository.findByUsername(user.getUsername())!=null)
	        return false; // El usuario ya existe
	  
		userRepository.save(user);
		return true;	
	}
	
	public User getUser(String username) throws Exception{
		return userRepository.findByUsername(username);
	}
	
	public void deleteUser(int id) throws Exception {
		userRepository.deleteById(id);
	}
}
