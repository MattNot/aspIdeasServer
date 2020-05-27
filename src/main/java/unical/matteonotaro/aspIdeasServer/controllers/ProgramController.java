package unical.matteonotaro.aspIdeasServer.controllers;

//import it.unical.mat.embasp.languages.asp.AnswerSet;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import unical.matteonotaro.aspIdeasServer.configurations.ASPHandler;
import unical.matteonotaro.aspIdeasServer.repository.ProjectRepository;
import unical.matteonotaro.aspIdeasServer.repository.UserRepository;

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
    @GetMapping(value = "api/evaluate")
    public Object eval(String text) {
        log.info(text);
//        return null;
        return ASPHandler.getInstance().startGuess(text);
    }

//    @PostMapping(value = "api/{engine}/evaluateProgram", consumes = "application/json", produces = "application/json")
//    public List<AnswerSet> evaluate(@RequestBody ASPInput in, @PathVariable String engine) {
//        List<AnswerSet> answerSets = DLVHandler.getInstance().startGuess(new ArrayList<>(), in.getInputProgram());
//        log.info(String.valueOf(answerSets));
//        return answerSets;
//    }
}
