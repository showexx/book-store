package showex.store.manager.repository;

import org.springframework.data.repository.CrudRepository;
import showex.store.manager.entity.ShowexStoreUser;

import java.util.Optional;

public interface BookStoreUserRepository extends CrudRepository<ShowexStoreUser, Integer> {
    Optional<ShowexStoreUser> findByUsername(String username);
}
