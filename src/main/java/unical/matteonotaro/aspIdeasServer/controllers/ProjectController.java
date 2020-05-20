package unical.matteonotaro.aspIdeasServer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unical.matteonotaro.aspIdeasServer.dto.ASPInput;
import unical.matteonotaro.aspIdeasServer.dto.Project;
import unical.matteonotaro.aspIdeasServer.dto.User;
import unical.matteonotaro.aspIdeasServer.repository.ProjectRepository;
import unical.matteonotaro.aspIdeasServer.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@Slf4j
public class ProjectController {

    final
    HttpServletRequest request;

    final
    UserRepository userRepository;

    final ProjectRepository projectRepository;

    public ProjectController(UserRepository userRepository, ProjectRepository projectRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.request = request;
    }


    @GetMapping(value = "api/projects/newProject/{name}")
    public String createNewProject(@PathVariable String name) {
        Project project = new Project();
        project.setName(name);
        project.setPrograms(new ArrayList<>());
        projectRepository.save(project);
        Optional<User> optionalUser = userRepository.findById((String) request.getSession().getAttribute("USER_ID"));
        if (optionalUser.isPresent()) {
            User u = optionalUser.get();
            u.getRelatedProjects().add(project.getId());
            u.setRelatedProjects(u.getRelatedProjects());
            userRepository.save(u);
        }
        return project.getId();
    }

    @GetMapping(value = "api/projects/{project}/newFile/{name}")
    public String createNewFile(@PathVariable String project, @PathVariable String name){
        Optional<Project> optionalProject = projectRepository.findById(project);
        if (optionalProject.isPresent()){
            Project tempProj = optionalProject.get();
            tempProj.getPrograms().add(new ASPInput(name,"",new ArrayList<>()));
            projectRepository.save(tempProj);
            return tempProj.getId();
        }
        return "Project doesn't exists";
    }

    @PostMapping(value = "api/projects/{project}/save")
    public void saveProject(@RequestBody ArrayList<ASPInput> programs, @PathVariable String project){
        Optional<Project> optionalProject = projectRepository.findById(project);
        if (optionalProject.isPresent()){
            Project old = optionalProject.get();
            old.setPrograms(programs);
            projectRepository.save(old);
        }
    }

    @GetMapping(value = "api/projects")
    public ArrayList<Project> getProjects(HttpServletRequest request) {
        ArrayList<String> pList = null;
        ArrayList<Project> projects = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById((String) request.getSession().getAttribute("USER_ID"));
        if (optionalUser.isPresent()) {
            pList = optionalUser.get().getRelatedProjects();
        }
        if (pList != null) {
            pList.forEach(projectId -> {
                Optional<Project> optionalProject = projectRepository.findById(projectId);
                optionalProject.ifPresent(projects::add);
            });
        }
        return projects;
    }

}
