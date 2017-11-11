package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edin on 09.11.2017..
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

}
