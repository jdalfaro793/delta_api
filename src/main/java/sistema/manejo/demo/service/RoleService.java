package sistema.manejo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.manejo.demo.model.Role;
import sistema.manejo.demo.model.User;
import sistema.manejo.demo.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	
	public boolean saveRole(Role role) {
		
		if(roleRepository.findByName(role.getName())!=null)
			return false;  // El rol ya existe
		
		
		roleRepository.save(role);
		return true;
	}
	
	
	public Role getRole(String name) throws Exception{
		return roleRepository.findByName(name);
	}
	
	public void deleteRole(int id) throws Exception {
		roleRepository.deleteById(id);
	}

}
