package com.letscode.starwarsapi;

import com.letscode.starwarsapi.dtos.InventoryDto;
import com.letscode.starwarsapi.dtos.LocalizationDto;
import com.letscode.starwarsapi.dtos.RebelDto;
import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RebelControllerTests {
    @LocalServerPort
    private int port;

    @MockBean
    private RebelRepository rebelRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnExistingRebelInsideGetAll() throws Exception {
        //Given
        LocalizationModel firstLocalization = new LocalizationModel(1L, 1L, "firstBase");
        InventoryModel firstInventory = new InventoryModel(1, 1, 1, 1);
        RebelModel firstRebel = new RebelModel("Gustavo", 25, GenderEnum.MALE.getGender(), firstLocalization, firstInventory);
        rebelRepository.save(firstRebel);
        ResponseEntity<List> response = restTemplate.getForEntity("/rebels/", List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().contains(firstRebel));
    }

    @Test
    public void shouldReturnEmptyListByDelete() throws Exception {
        //Given
        LocalizationModel firstLocalization = new LocalizationModel(1L, 1L, "firstBase");
        InventoryModel firstInventory = new InventoryModel(1, 1, 1, 1);
        RebelModel firstRebel = new RebelModel("Gustavo", 25, GenderEnum.MALE.getGender(), firstLocalization, firstInventory);
        rebelRepository.save(firstRebel);
        restTemplate.delete("/"+firstRebel.getId());
        ResponseEntity<List> response = restTemplate.getForEntity("/rebels/", List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(!response.getBody().contains(firstRebel));
    }

    @Test
    public void validRebelPostShouldntReturnNull() throws Exception {
        InventoryDto invDto = new InventoryDto(10,20,30,10);
        LocalizationDto locDTO = new LocalizationDto(30L,20L,"Base Nova");
        RebelDto rebelDTO = new RebelDto("Guilherme", 21,"MALE",locDTO,invDto);

        HttpEntity<RebelDto> request = new HttpEntity<>(rebelDTO);
        RebelModel rebelSaved = restTemplate.postForObject("/rebels", request, RebelModel.class);
        assertThat(rebelSaved != null);
    }
}
