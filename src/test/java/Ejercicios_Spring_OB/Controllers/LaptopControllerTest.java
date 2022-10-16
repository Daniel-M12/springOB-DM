package Ejercicios_Spring_OB.Controllers;

import Ejercicios_Spring_OB.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops",Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Laptop[].class,response.getBody().getClass());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptop/1",Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "marca": "New Test Laptop",
                    "modelo": "TEST417",
                    "anioFabricacion": 2022
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop laptopResponse = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals(1L,laptopResponse.getId());
        assertEquals("New Test Laptop", laptopResponse.getMarca());
        assertEquals("TEST417", laptopResponse.getModelo());
        assertEquals(2022, laptopResponse.getAnioFabricacion());
    }

    @Test
    void update() {

        ResponseEntity<Laptop> responseFromPOST = insertTestLaptop(); //Separado en otro método, pues puede ser usado en general para ingresar una nueva laptop devolviendo un ResponseEntity

        assertEquals(HttpStatus.OK, responseFromPOST.getStatusCode()); //Prueba que el PUT está funcionando

        responseFromPOST = insertTestLaptop();
        assertEquals(HttpStatus.OK, responseFromPOST.getStatusCode()); //Hasta aquí en total se ingresaron 2 laptops

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String jsonPUT = """
                {
                    "id": 2,
                    "marca": "New Test Laptop",
                    "modelo": "TEST417",
                    "anioFabricacion": 2022
                }
                """;

        HttpEntity<String> requestPUT = new HttpEntity<>(jsonPUT, headers);

        ResponseEntity<Laptop> responsePUT = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, requestPUT, Laptop.class);

        Laptop laptopResponse = responsePUT.getBody();

        assertEquals(2L,laptopResponse.getId());
        assertEquals("New Test Laptop", laptopResponse.getMarca());
        assertEquals("TEST417", laptopResponse.getModelo());
        assertEquals(2022, laptopResponse.getAnioFabricacion());
    }

    private ResponseEntity<Laptop> insertTestLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String jsonPOST = """
                {
                    "marca": "POST Laptop",
                    "modelo": "TEST000",
                    "anioFabricacion": 3000
                }
                """;

        HttpEntity<String> requestPOST = new HttpEntity<>(jsonPOST,headers);

        return testRestTemplate.exchange("/api/laptops", HttpMethod.POST, requestPOST, Laptop.class);
    }

}