package unical.matteonotaro.aspIdeasServer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unical.matteonotaro.aspIdeasServer.configurations.ASPHandler;
import unical.matteonotaro.aspIdeasServer.dto.EvaluateWrapper;
import unical.matteonotaro.aspIdeasServer.dto.TestWrapper;
import unical.matteonotaro.aspIdeasServer.dto.testCases.ASPTestCase;
import unical.matteonotaro.aspIdeasServer.repository.ProjectRepository;
import unical.matteonotaro.aspIdeasServer.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;

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

    @PostMapping(value = "api/evaluate")
    public ArrayList<String> eval(@RequestBody EvaluateWrapper evaluateWrapper) {
        return ASPHandler.getInstance().startGuess(evaluateWrapper.getInput().getInputProgram(), evaluateWrapper.getOptions());
    }

    @PostMapping(value = "api/test")
    public HashMap<String, ArrayList<Object>> test(@RequestBody TestWrapper testWrapper){
        HashMap<String, ArrayList<Object>> testResults = new HashMap<>();
        log.error(String.valueOf(testWrapper.getTestCases()));
        for (ASPTestCase testCase : testWrapper.getTestCases()){
//            log.info(testCase.toString());
            testResults.put(testCase.getName(), ASPHandler.getInstance().startTest(testWrapper.getOptions(), testCase));
        }
        return testResults;
    }

}
