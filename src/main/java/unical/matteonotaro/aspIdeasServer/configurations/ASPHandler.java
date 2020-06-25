package unical.matteonotaro.aspIdeasServer.configurations;


import it.unical.mat.wrapper.*;
import lombok.extern.slf4j.Slf4j;
import unical.matteonotaro.aspIdeasServer.dto.testCases.ASPTestCase;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
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
            if (dlvInvocation.getErrors().size() > 0 && options.getExecutor().equals("dlv2")) {
                List<DLVError> errors = dlvInvocation.getErrors();
                ArrayList<String> err = new ArrayList<>();
                errors.forEach(er -> err.add(er.getText()));
                return err;
            }
            return modelHandler.getResults();
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> startTest(ASPOptions options, ASPTestCase testCase) {
        inputProgram = new DLVInputProgramImpl();
        HashMap<String, Boolean> assertionsResults = new HashMap<>();
        ASPModelHandler modelHandler = new ASPModelHandler();
        try {
            String p = testCase.getProgram();
            for (ASPAssertion assertion : testCase.getAssertions()) {
                dlvInvocation = DLVWrapper.getInstance().createInvocation(pathToExe + options.getExecutor(),
                        options.getExecutor().equals("dlv2") ? SolverType.DLV2 : SolverType.CLINGO);
                dlvInvocation.subscribe(modelHandler);
                String actualProgram = assertion.generateTester(p);
                inputProgram.addText(actualProgram);
//                try{
                dlvInvocation.setInputProgram(inputProgram);
//                }catch (Exception e){
//                    System.out.println(e.toString());
//                }finally {
                log.error(assertion.getName() + "--> \n" + dlvInvocation.getInputProgram().getText());
                dlvInvocation.addOption("-n " + assertion.getK());
                dlvInvocation.run();
                dlvInvocation.waitUntilExecutionFinishes();
                log.error(modelHandler.getModels().toString());
                log.error(assertion.getClass().getTypeName());
                assertionsResults.put(assertion.getName(), assertion.check(modelHandler.getModels()));
                inputProgram.clean();
                dlvInvocation.resetState();
//                }
            }
            return new ArrayList<>(Arrays.asList(modelHandler.getResults(), assertionsResults));
//            if (dlvInvocation.getErrors().size() > 0){
//                List<DLVError> errors = dlvInvocation.getErrors();
//                ArrayList<Object> err = new ArrayList<>();
//                errors.forEach(er -> err.add(er.getText()));
//                return err;
//            }
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkOption(String option, String executor) {
        inputProgram = new DLVInputProgramImpl();
        inputProgram.addText("a.");
        dlvInvocation = DLVWrapper.getInstance().createInvocation(pathToExe + executor,
                executor.equals("dlv2") ? SolverType.DLV2 : SolverType.CLINGO);
        ASPModelHandler modelHandler = new ASPModelHandler();
        try {
            dlvInvocation.subscribe(modelHandler);
            dlvInvocation.setInputProgram(inputProgram);
            dlvInvocation.addOption(option);
            dlvInvocation.run();
            dlvInvocation.waitUntilExecutionFinishes();
            /*FIXME: Clingo always got error*/
//            log.error(dlvInvocation.getErrors().get(0).getText());
            return dlvInvocation.getErrors().size() == 0;
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

