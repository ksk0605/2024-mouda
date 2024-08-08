package mouda.backend.auth.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import mouda.backend.auth.dto.request.LoginRequest;
import mouda.backend.config.DatabaseCleaner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

	@Autowired
	AuthController authController;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@AfterEach
	void cleanUp() {
		databaseCleaner.cleanUp();
	}

	@DisplayName("로그인 하기")
	@Test
	void login() {
		LoginRequest request = new LoginRequest("테바");

		RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.body(request)
			.when().post("/v1/auth/login")
			.then().log().all()
			.statusCode(200);
	}
}