package sistema.manejo.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.manejo.demo.dto.RoleDTO;
import sistema.manejo.demo.model.Role;
import sistema.manejo.demo.service.RoleService;


@RestController
@RequestMapping("/roles")
public class RoleController {
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/getAll")
	public Map<String, Object> getAllRoles() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("estado", false);
		map.put("mensaje", "No se pudo obtener roles");

		try {
			List<Role> roles = roleService.getAllRoles();
			
			map.put("estado", true);
			map.put("roles", roles);
			map.put("mensaje", "Roles obtenidos con exito");
		} catch (Exception e) {
			log.error("Error al obtener roles: ", e);
		}
		
		return map;
	}
		
	
	@PostMapping("/newRole")
	public Map<String, Object> createRole(@RequestBody RoleDTO roledto) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("estado", false);
		map.put("mensaje", "No se pudo registrar el rol");
		try {
			
			if(roledto.getName().isEmpty() || roledto.getName()==null || roledto.getDescription().isEmpty() || roledto.getName()==null) {
				map.put("mensaje", "tiene datos nulos o inválidos");
			}else {
				
				Role role = new Role();
				role.setDescription(roledto.getDescription());
				role.setName(roledto.getName());
				
				boolean res = roleService.saveRole(role);
				
				map.put("estado", res);
				map.put("mensaje", !res?"No se registró el rol":"Se registró el rol correctamente");
				
			}
			
		} catch (Exception e) {
			log.error("Ocurrió un error al registrar rol: ", e);
		}

		return map;
	}

	
	@DeleteMapping("/{id}")
	public Map<String, Object> deleteRole(@PathVariable("id") int codigo) {
		Map<String, Object> map = new HashMap<>();

	
		map.put("estado", false);
		map.put("mensaje", "No se pudo eliminar el rol");

		try {
			
			boolean res=roleService.deleteRole(codigo);
			
			map.put("estado", res);
			map.put("mensaje", !res? "No se pudo eliminar el rol":"Rol eliminado");
	
		} catch (Exception e) {

			log.error("Error al intentar eliminar rol: ", e);
		}

		return map;
	}


}
