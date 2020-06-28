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
        project.setCliOptions(new ArrayList<>());
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
            ASPInput newFile = new ASPInput(name, "", tempProj.getId());
            tempProj.getPrograms().add(newFile);
            projectRepository.save(tempProj);
            return tempProj.getId();
        }
        return "Project doesn't exists";
    }

    @PostMapping(value = "api/projects/{project}/save")
    public void saveProject(@RequestBody ArrayList<ASPInput> programs, @PathVariable String project){
        log.info(String.valueOf(programs));
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

    @GetMapping(value = "api/projects/{project}/cliOptions/add/{value}")
    public void addCliOptions(@PathVariable String project, @PathVariable String value) {
        Optional<Project> optionalProject = projectRepository.findById(project);
        optionalProject.ifPresent(project1 -> project1.getCliOptions().add(value));
        optionalProject.ifPresent(projectRepository::save);
    }

    @GetMapping(value = "api/projects/{project}/cliOptions/remove/{value}")
    public void removeCliOptions(@PathVariable String project, @PathVariable String value) {
        Optional<Project> optionalProject = projectRepository.findById(project);
        optionalProject.ifPresent(project1 -> project1.getCliOptions().remove(value));
        optionalProject.ifPresent(projectRepository::save);
    }

    @GetMapping(value = "api/projects/{project}/delete/{name}")
    public void deleteFile(@PathVariable String project, @PathVariable String name) {
        Optional<Project> optionalProject = projectRepository.findById(project);
        if (optionalProject.isPresent()) {
            Project p = optionalProject.get();
            ArrayList<ASPInput> aspInputs = p.getPrograms();
            aspInputs.removeIf(input -> input.getName().equals(name));
            p.setPrograms(aspInputs);
            projectRepository.save(p);
        }
    }

    @GetMapping(value = "api/projects/{project}/delete")
    public void deleteProject(@PathVariable String project) {
        Optional<Project> optionalProject = projectRepository.findById(project);
        optionalProject.ifPresent(projectRepository::delete);
    }

    @GetMapping(value = "api/projects/{project}/rename/{value}")
    public void renameProject(@PathVariable String project, @PathVariable String value) {
        Optional<Project> optionalProject = projectRepository.findById(project);
        optionalProject.ifPresent(project1 -> project1.setName(value));
    }

    @GetMapping(value = "api/projects/{project}/rename/file/{filename}/{value}")
    public void renameFile(@PathVariable String project, @PathVariable String value, @PathVariable String filename) {
        Optional<Project> optionalProject = projectRepository.findById(project);
        if (optionalProject.isPresent()) {
            Project p = optionalProject.get();
            ArrayList<ASPInput> aspInputs = p.getPrograms();
            for (ASPInput in : aspInputs) {
                if (in.getName().equals(filename))
                    in.setName(value);
            }
            p.setPrograms(aspInputs);
            projectRepository.save(p);
        }
    }


}
