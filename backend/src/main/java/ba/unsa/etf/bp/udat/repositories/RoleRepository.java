package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edin on 11.11.2017..
 */
@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
