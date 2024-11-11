package com.amagana.settings_service.repository;

import com.amagana.settings_service.entity.Category;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;

    Category category;
	
	@BeforeEach
	void setUp() {

		category = Category.builder()
				.categoryDescription("Roman in book")
				.categoryName("Roman")
				.build();
		categoryRepository.save(category);
	}

	@Test
	@Order(1)
	void shouldFindAllCategoryReturnAllCategory() {
		List<Category> categoryRequest = categoryRepository.findAll();
		assertEquals(categoryRepository.count(), categoryRequest.size());
		assertEquals("Roman", categoryRequest.getFirst().getCategoryName());
		assertNotNull(categoryRequest);
	}
	
	@Test
	@Order(2)
	void shouldFindCategoryByIdReturnCategory() {
		Category categoryRequest = categoryRepository.findById(1L).orElse(null);
        assertEquals("Roman in book", categoryRequest.getCategoryDescription());
		assertEquals("Roman", categoryRequest.getCategoryName());
	}

	@Test
	@Order(3)
	void shouldFindCategoryByIdReturnNull() {
		Category categoryRequest = categoryRepository.findById(37L).orElse(null);
		assertNull(categoryRequest);
	}
	
	@Test
	@Order(4)
	void shouldAddCategoryInDatabase() {
		Category category2 = Category.builder()
				.categoryDescription("Romantique")
				.categoryName("Harlequin")
				.build();
		Category categoryRequest = categoryRepository.save(category2);
		assertEquals("Romantique", categoryRequest.getCategoryDescription());
		assertEquals("Harlequin", categoryRequest.getCategoryName());
	}
	
	@Test
	@Order(5)
	void shouldUpdateCategoryReturnUpdateCategory() {
		category.setCategoryDescription("Fiction");
		category.setCategoryName("Film");;
		Category categoryRequest = categoryRepository.save(category);
		assertEquals("Fiction", categoryRequest.getCategoryDescription());
		assertEquals("Film", categoryRequest.getCategoryName());
	}
	
	@Test
	@Order(6)
	void shouldDeleteCategoryById() {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryRepository.delete(category);
		Mockito.verify(categoryRepository).delete(category);
	}
	
}
