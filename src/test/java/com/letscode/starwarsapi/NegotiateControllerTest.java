package com.letscode.starwarsapi;

import com.letscode.starwarsapi.controllers.NegotiateController;
import com.letscode.starwarsapi.services.InventoryService;
import com.letscode.starwarsapi.services.RebelService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@WebMvcTest
public class NegotiateControllerTest {
    @Autowired
    private NegotiateController negotiateController;
    @MockBean
    private InventoryService inventoryService;
    @MockBean
    private RebelService rebelService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.negotiateController);

    }

    @Test
    public void shouldReturnSuccess_whenToNegotiateItems(){

        //when(this.inventoryService.).thenReturn();

        RestAssuredMockMvc.given().accept(ContentType.JSON).when().post("/negotiate/{donorId}/negotiateTo/{receiverId" +
                "}").then().statusCode(HttpStatus.OK.value());

    }
}
