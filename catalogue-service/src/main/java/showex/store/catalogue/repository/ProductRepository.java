package showex.store.catalogue.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import showex.store.catalogue.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(name = "Product.findAllByTitleLikeIgnoringCase", nativeQuery = true)
    Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
