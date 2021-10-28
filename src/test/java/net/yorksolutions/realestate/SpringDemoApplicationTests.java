package net.yorksolutions.realestate;

import net.yorksolutions.realestate.controller.RealEstateController;
import net.yorksolutions.realestate.model.RealEstate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringDemoApplicationTests {
    @Autowired
    private RealEstateController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void realEstateControllerLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void realEstateGetAll() {
        RealEstate[] realEstateAll = restTemplate.getForObject("http://localhost:"+ port + "/realestate/all",
                RealEstate[].class);
        assertThat(realEstateAll).isNotNull();
        assertThat(realEstateAll).isNotEmpty();
    }

    @Test
    void realEstateGetByRows() {
        int expectedRows = 1;
        RealEstate[] realEstateRows = restTemplate.getForObject(
                "http://localhost:"+ port + "/realestate/getByRowAmount?rows=" + expectedRows,
                RealEstate[].class
        );
        assertThat(realEstateRows).isNotNull();
        assertThat(realEstateRows).isNotEmpty();
        assertThat(realEstateRows.length == expectedRows);
        assertThat(realEstateRows).hasSize(expectedRows);
    }

    @Test
    void searchTest() {


        String testValue = "Madison";
        String baseURL = "http://localhost:"+ port + "/realestate/";

        //INCLUDE THE FOLLOWING IF NO ROW WITH THE TEST VALUE EXISTS
//        RealEstate realEstate = new RealEstate();
//        realEstate.setFname(testValue);
//        String response = restTemplate.postForObject(baseURL + "add", realEstate, String.class);
//        assertThat(response).isEqualTo("success");

        RealEstate[] realEstates = restTemplate.getForObject(baseURL+ "search?fname=" + testValue, RealEstate[].class);
        assertThat(realEstates).isNotNull();
        assertThat(realEstates).hasSizeGreaterThan(0);

        for (RealEstate realEstate1 : realEstates)
            assertThat(realEstate1.getFname()).isEqualTo(testValue);
        }
    }

