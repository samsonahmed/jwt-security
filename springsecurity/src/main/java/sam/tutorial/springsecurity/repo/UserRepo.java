package sam.tutorial.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sam.tutorial.springsecurity.domain.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser,Long> {

    AppUser findByUserName(String name);
}
