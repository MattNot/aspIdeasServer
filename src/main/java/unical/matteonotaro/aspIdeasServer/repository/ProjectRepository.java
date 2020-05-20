package unical.matteonotaro.aspIdeasServer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import unical.matteonotaro.aspIdeasServer.dto.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}
