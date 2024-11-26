package sistema.manejo.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {DeltaApplication.class})
@RunWith(SpringRunner.class)
public class DeltaApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(DeltaApplicationTests.class);

	@Test
	public void contextLoads() {
		log.info("Ejecutando contextLoads()");
	}

}
