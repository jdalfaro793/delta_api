package sistema.manejo.demo.service;

import java.util.Date;
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
	    Role existingRole = roleRepository.findByName(role.getName());

	    if (existingRole != null) {
	        if (existingRole.getStatus() == 1) {
	            // El rol ya está activo, no hacemos nada
	            return false;
	        } else {
	            // Reactivamos el rol inactivo
	            existingRole.setStatus(1);
	            existingRole.setModification(new Date());
	            roleRepository.save(existingRole);
	            return true;
	        }
	    } else {
	        // Creamos un nuevo rol
	        role.setStatus(1);
	        Date currentDate = new Date();
	        role.setRegistration(currentDate);
	        role.setModification(currentDate);

	        roleRepository.save(role);
	        return true;
	    }
	}
	
	public Role getRole(String name) throws Exception {
		return roleRepository.findByName(name);
	}
	
	public boolean deleteRole(Integer roleId) throws Exception {
	    Optional<Role> roleOptional = roleRepository.findById(roleId);

	    if (roleOptional.isPresent()) {
	        Role role = roleOptional.get();
	        
	        if(role.getStatus()==1) {
		        role.setStatus(0);  				// Marcar el rol como inactivo en lugar de eliminarlo
		        roleRepository.save(role);  		// Guardar los cambios en la base de datos
		        return true;  						// Eliminación lógica exitosa
	        }
	    }

	    return false;		// Rol no encontrado
	}

    public List<Role> getAllRoles() throws Exception {
        return roleRepository.findAll();
    }
}
