package unical.matteonotaro.aspIdeasServer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unical.matteonotaro.aspIdeasServer.dto.ASPInput;
import unical.matteonotaro.aspIdeasServer.dto.Project;
import unical.matteonotaro.aspIdeasServer.dto.User;
import unical.matteonotaro.aspIdeasServer.repository.ProjectRepository;
import unical.matteonotaro.aspIdeasServer.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@Slf4j
public class ProgramController {

    final
    UserRepository userRepository;

    final
    ProjectRepository projectRepository;

    public ProgramController(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping(value = "api/saveProgram")
    public void saveProgram(@RequestBody ASPInput input, @RequestBody String projectId, HttpServletRequest request) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            Project project1 = project.get();
            projectRepository.save(project1);
        }
    }

    @PostMapping(value = "api/evaluateProgram", consumes = "application/json", produces = "application/json")
    public User evaluate(@RequestBody ASPInput in) {
        return null;
    }
}
