package sam.tutorial.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sam.tutorial.springsecurity.domain.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByName(String name);

}
