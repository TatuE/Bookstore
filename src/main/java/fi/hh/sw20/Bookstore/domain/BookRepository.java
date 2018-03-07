package fi.hh.sw20.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findById(@Param("id") long id);
	
}
