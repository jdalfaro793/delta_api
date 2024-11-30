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

import sistema.manejo.demo.model.User;
import sistema.manejo.demo.service.UserService;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);

	@Autowired
	UserService userService;
	
	@Test
	public void alta() throws Exception {
		
		User u = new User();
		
		u.setEmail("darioalfarounju@gmail.com");
		u.setModification(new Date());
		u.setRegistration(new Date());
		u.setPassword("12345678");
		u.setStatus(1);
		u.setUsername("josdar");
		

		if(userService.saveUser(u))
			assertTrue(userService.deleteUser(userService.getUser(u.getUsername()).getId()) ==true);
		else
			log.info("ya esta registrado");
	}

}
