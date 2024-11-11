package com.amagana.settings_service.repository;

import com.amagana.settings_service.entity.Address;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties =  {
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("settings-service-test")
class AddressRepositoryTest {
	
	@Autowired
	AddressRepository addressRepository;


	
	Address address;
	
	@BeforeEach
	void setUp() {

		addressRepository.save(Address.builder().addressCity("Mulhenbach").addressEmail("donald9do@gmail.com")
				.addressName("Huberty").addressNumber(37).addressPhone("495463737").addressProfessionalPhone("4345664545")
				.build());
		addressRepository.save(Address.builder().addressCity("Lyon").addressEmail("evrard@gmail.com")
				.addressName("gare").addressNumber(37).addressPhone("755463737").addressProfessionalPhone("9845664545")
				.build());
	    addressRepository.save(Address.builder().addressCity("Paris").addressEmail("donald9do@gmail.com")
				.addressName("berlin").addressNumber(37).addressPhone("9815463737").addressProfessionalPhone("5645664545")
				.build());

	}

	@Test
	@Order(1)
	@Rollback(value = false)
	void testAddAddressInDatabase() {
		Address address2 = Address.builder()
				.addressCity("Hamilus")
				.addressEmail("evrard@gmail.com")
				.addressName("Lux")
				.addressNumber(40)
				.addressPhone("62157663737")
				.addressProfessionalPhone("6845664545")
				.build();
		Address addressRequest = addressRepository.save(address2);
		assertEquals(40, addressRequest.getAddressNumber());
		assertEquals("Hamilus", addressRequest.getAddressCity());
	}

	@Test
	@Order(3)
	@Rollback(value = false)
	void testFindAllAddress() {
		List<Address> addressRequest = addressRepository.findAll();
		assertEquals(10, addressRequest.size());
		assertEquals("Mulhenbach", addressRequest.getFirst().getAddressCity());
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	void testFindAddressById() {
		Address addressRequest = addressRepository.findById(1L).orElseThrow();
		assertEquals(37, addressRequest.getAddressNumber());
		assertEquals("Mulhenbach", addressRequest.getAddressCity());
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	void shouldUpdateAddress() {
		Address address1 = addressRepository.findById(1L).orElse(null);
		address1.setAddressCity("Hamilus");
		address1.setAddressNumber(40);
		Address addressRequest = addressRepository.save(address1);
		assertEquals(40, addressRequest.getAddressNumber());
		assertEquals("Hamilus", addressRequest.getAddressCity());
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	void shouldUpdateAddressThrow() {
		Address address1 = addressRepository.findById(40L).orElse(null);
		assertNull(address1);
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	void shouldDeleteAddressById() {
        addressRepository.findById(4L).ifPresent(address1 -> addressRepository.deleteById(address1.getId()));
        assertFalse(addressRepository.findById(4L).isPresent());
	}
}
