package unical.matteonotaro.aspIdeasServer.controllers;

//import it.unical.mat.embasp.languages.asp.AnswerSet;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unical.matteonotaro.aspIdeasServer.configurations.ASPHandler;
import unical.matteonotaro.aspIdeasServer.dto.EvaluateWrapper;
import unical.matteonotaro.aspIdeasServer.repository.ProjectRepository;
import unical.matteonotaro.aspIdeasServer.repository.UserRepository;

import java.util.ArrayList;

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

    @SneakyThrows
    @PostMapping(value = "api/evaluate")
    public ArrayList<String> eval(@RequestBody EvaluateWrapper evaluateWrapper) {
        return ASPHandler.getInstance().startGuess(evaluateWrapper.getInput().getInputProgram(), evaluateWrapper.getOptions());
    }
}
