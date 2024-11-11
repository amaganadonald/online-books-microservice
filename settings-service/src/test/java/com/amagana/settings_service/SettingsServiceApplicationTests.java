package com.amagana.settings_service;

import com.amagana.settings_service.dto.*;
import com.amagana.settings_service.entity.Category;
import com.amagana.settings_service.enums.StatusResponse;
import com.amagana.settings_service.repository.AddressRepository;
import com.amagana.settings_service.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("settings-service-test")
class SettingsServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ObjectMapper objectMapper;


	 CategoryRequestDTO categoryRequestDTO;
	 AddressRequestDTO addressRequestDTO;
	 Long categoryId;
	 Long addressId;
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AddressRepository addressRepository;


	@BeforeEach
	void beforeEach() {
		categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman in  book")
				.categoryName("Roman")
				.createdAt(LocalDateTime.now())
				.createdBy("Donald")
				.lastUpdateDate(LocalDateTime.now())
				.lastUpdatedBy("Donald")
				.build();
		addressRequestDTO = AddressRequestDTO.builder()
				.addressCity("Huberty")
				.addressName("Jean Pièrre")
				.addressEmail("huberty@lux.lu")
				.addressNumber(37)
				.createdAt(LocalDateTime.now())
				.createdBy("Noe")
				.addressPhone("6894555")
				.addressProfessionalPhone("698744")
				.build();
		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/v1/category", HttpMethod.POST, new HttpEntity<>(categoryRequestDTO),
				new ParameterizedTypeReference<>() {});
		categoryId = response.getBody().getResults().id();
		ResponseEntity<ApiResponse<AddressResponseDTO>> response1 = testRestTemplate.exchange("/api/v1/address", HttpMethod.POST, new HttpEntity<>(addressRequestDTO),
				new ParameterizedTypeReference<>() {});
		addressId = response1.getBody().getResults().id();
	}


	@Test
	@Order(1)
	@Rollback(value = false)
	void shouldAddNewCategory() {

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/v1/category", HttpMethod.POST,
				new HttpEntity<>(categoryRequestDTO), new ParameterizedTypeReference<>() {});
		ApiResponse<CategoryResponseDTO> categoryResponseDTO = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assert categoryResponseDTO != null;
		AssertionsForClassTypes.assertThat(categoryResponseDTO.getStatus()).isEqualTo(StatusResponse.SUCCESS);
		AssertionsForClassTypes.assertThat(categoryResponseDTO.getResults().categoryDescription()).isEqualTo("Roman in  book");
		AssertionsForClassTypes.assertThat(categoryResponseDTO.getResults().categoryName()).isEqualTo("Roman");
	}

	@Test
	@Order(2)
	void shouldGetAllCategory() {
		ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> response = testRestTemplate.exchange("/api/v1/category", HttpMethod.GET,
				null,
				new ParameterizedTypeReference<>() {});
		ApiResponse<List<CategoryResponseDTO>> content = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert content != null;
        AssertionsForClassTypes.assertThat(content.getResults().size()).isEqualTo(3);
	}

	@Test
	@Order(3)
	void shouldGetCategoryById() {

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/v1/category/"+categoryId, HttpMethod.GET,
				null, new ParameterizedTypeReference<>() {});
		ApiResponse<CategoryResponseDTO> categoryResponseDTO = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assert categoryResponseDTO != null;
		AssertionsForClassTypes.assertThat(categoryResponseDTO.getResults().categoryName()).isEqualTo("Roman");
	}

	@Test
	@Order(4)
	void shouldUpdateCategory()  {
		CategoryRequestDTO categoryRequestDTO1 = CategoryRequestDTO.builder()
				.categoryDescription("Action")
				.categoryName("Redemption")
				.build();
		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/v1/category/" + categoryId,
				HttpMethod.PUT, new HttpEntity<>(categoryRequestDTO1), new ParameterizedTypeReference<>() {});
		ApiResponse<CategoryResponseDTO> categoryResponseDTO = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assert categoryResponseDTO != null;
		AssertionsForClassTypes.assertThat(categoryResponseDTO.getResults().categoryName()).isEqualTo("Redemption");
	}

	@Test
	void shouldAddNewAddress() {
		ResponseEntity<ApiResponse<AddressResponseDTO>> response = testRestTemplate.exchange("/api/v1/address",
				HttpMethod.POST, new HttpEntity<>(addressRequestDTO), new ParameterizedTypeReference<>() {});
		ApiResponse<AddressResponseDTO> addressResponseDTO = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assert addressResponseDTO != null;
		AssertionsForClassTypes.assertThat(addressResponseDTO.getResults().addressCity()).isEqualTo("Huberty");
	}

	@Test
	void shouldGetAllAddress() {
		ResponseEntity<ApiResponse<List<AddressResponseDTO>>> response =testRestTemplate.exchange("/api/v1/address",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<List<AddressResponseDTO>>>() {});
		ApiResponse<List<AddressResponseDTO>> content = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert content != null;
        AssertionsForClassTypes.assertThat(content.getResults().size()).isEqualTo(3);
	}

	@Test
	void shouldGetAddressById() {
		ResponseEntity<ApiResponse<AddressResponseDTO>> response = testRestTemplate.exchange("/api/v1/address/"+addressId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ApiResponse<AddressResponseDTO>>() {});
		ApiResponse<AddressResponseDTO> addressResponseDTO = response.getBody();
		AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assert addressResponseDTO != null;
		AssertionsForClassTypes.assertThat(addressResponseDTO.getResults().addressCity()).isEqualTo("Huberty");
	}

}
