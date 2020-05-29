package unical.matteonotaro.aspIdeasServer.configurations;


import it.unical.mat.wrapper.*;
import unical.matteonotaro.aspIdeasServer.dto.testCases.ASPTestCase;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ASPHandler {
    private static String pathToExe = null;
    private static ASPHandler instance = null;
    private DLVInputProgram inputProgram;
    private DLVInvocation dlvInvocation;
    HashMap<String, String> testCasesResults = new HashMap<>();

    private ASPHandler() {
        this.setPathToExe();
    }

    private void setPathToExe() {
        if (ASPHandler.pathToExe == null) {
            //Cannot do this on constructor 'cause File(".") doesn't exist, java reasons
            StringBuilder path = new StringBuilder(new File(".").getAbsolutePath());
            path.deleteCharAt(path.indexOf("."));
            pathToExe = path + "src" + File.separator + "main" +
                    File.separator + "resources" + File.separator + "libs" + File.separator;
        }
    }

    public static ASPHandler getInstance() {
        if (instance == null) {
            instance = new ASPHandler();
        }
        return instance;
    }

    public ArrayList<String> startGuess(String originalProgram, ASPOptions options) {
        inputProgram = new DLVInputProgramImpl();
        inputProgram.addText(originalProgram);
        dlvInvocation = DLVWrapper.getInstance().createInvocation(pathToExe + options.getExecutor(),
                options.getExecutor().equals("dlv2") ? SolverType.DLV2 : SolverType.CLINGO);

        ASPModelHandler modelHandler = new ASPModelHandler();
        try {
            dlvInvocation.subscribe(modelHandler);
            dlvInvocation.setInputProgram(inputProgram);
            dlvInvocation.addOption("-n " + options.getN());
            dlvInvocation.run();
            dlvInvocation.waitUntilExecutionFinishes();
            return modelHandler.getOutputModels();
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> startTest(ASPOptions options, ASPTestCase testCase) {
        inputProgram = new DLVInputProgramImpl();
        inputProgram.addText(testCase.getInput());
        HashMap<String, Boolean> assertionsResults = new HashMap<>();
        dlvInvocation = DLVWrapper.getInstance().createInvocation(pathToExe + options.getExecutor(),
                options.getExecutor().equals("dlv2") ? SolverType.DLV2 : SolverType.CLINGO);

        ASPModelHandler modelHandler = new ASPModelHandler();
        try {
            dlvInvocation.subscribe(modelHandler);
            for (ASPAssertion assertion : testCase.getAssertions()) {
                inputProgram.addText(assertion.generateTester(testCase.getProgram()));
                dlvInvocation.setInputProgram(inputProgram);
                dlvInvocation.addOption("-n 0");
                dlvInvocation.run();
                dlvInvocation.waitUntilExecutionFinishes();
                assertionsResults.put(assertion.getName(), assertion.check(modelHandler.getModels()));
                dlvInvocation.setInputProgram(null);
            }
            return new ArrayList<>(Arrays.asList(modelHandler.getOutputModels(), assertionsResults));
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}