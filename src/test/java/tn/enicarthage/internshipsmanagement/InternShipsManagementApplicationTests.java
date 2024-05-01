package tn.enicarthage.internshipsmanagement;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.UserRepos;
import tn.enicarthage.internshipsmanagement.security.AuthenticationService;
import tn.enicarthage.internshipsmanagement.security.JwtService;
import tn.enicarthage.internshipsmanagement.security.UserRequest;
import tn.enicarthage.internshipsmanagement.services.DepartmentService;

@SpringBootTest
class InternShipsManagementApplicationTests {

	@Test
	void contextLoads() {
	}
}
