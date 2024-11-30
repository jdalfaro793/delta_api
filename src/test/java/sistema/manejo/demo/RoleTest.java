package sistema.manejo.demo;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sistema.manejo.demo.model.Role;
import sistema.manejo.demo.model.User;
import sistema.manejo.demo.service.RoleService;
import sistema.manejo.demo.service.UserService;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class RoleTest {
	private static final Logger log = LoggerFactory.getLogger(RoleTest.class);

	@Autowired
	RoleService roleService;
	
	@Test
	public void alta() throws Exception {
		
		Role r = new Role();
	
		r.setDescription("ADMIN SYSTEM");
		r.setModification(new Date());
		r.setName("ADMIN");
		r.setRegistration(new Date());
		r.setStatus(1);
		
		
		if(roleService.saveRole(r))
			assertTrue(roleService.deleteRole(roleService.getRole(r.getName()).getId()) == true);
		else
			log.info("ya esta registrado");
		
	}

}
