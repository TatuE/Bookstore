package fi.hh.sw20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import fi.hh.sw20.Bookstore.domain.Category;
import fi.hh.sw20.Bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void findByNameShouldReturnCategory() {		
		List<Category> categories = repository.findByName("Drama");
		assertThat(categories).hasSize(1);		
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Romance");
		repository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
		List<Category> categories = repository.findByName("Romance");
		assertThat(categories).hasSize(1);	
	}
	
	@Test
	public void deleteCategory() {
		Category category = new Category("Romance2");
		repository.save(category);
		repository.delete(category);
		List<Category> categories = repository.findByName("Romance2");
		assertThat(categories).hasSize(0);		
	}

}
