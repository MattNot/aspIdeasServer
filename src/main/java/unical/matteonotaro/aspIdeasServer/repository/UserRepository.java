package unical.matteonotaro.aspIdeasServer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import unical.matteonotaro.aspIdeasServer.dto.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}
