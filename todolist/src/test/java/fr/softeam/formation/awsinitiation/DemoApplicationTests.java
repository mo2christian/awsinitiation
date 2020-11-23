package fr.softeam.formation.awsinitiation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"cloud.aws.credentials.instanceProfile = false"
})
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
