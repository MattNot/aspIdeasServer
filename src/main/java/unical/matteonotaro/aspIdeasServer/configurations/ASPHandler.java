package unical.matteonotaro.aspIdeasServer.configurations;


import it.unical.mat.dlv.program.Literal;
import it.unical.mat.wrapper.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ASPHandler {
    private static String pathToExe = null;
    private static ASPHandler instance = null;
    private DLVInputProgram inputProgram;
    private DLVInvocation dlvInvocation;
    private StringBuilder result = new StringBuilder("");

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

    public ArrayList<String> startGuess(String externalProgram, ASPOptions options) {
        inputProgram = new DLVInputProgramImpl();
        inputProgram.addText(externalProgram);
        ArrayList<String> models = new ArrayList<>();
        dlvInvocation = DLVWrapper.getInstance().createInvocation(pathToExe + options.getExecutor(), options.getExecutor().equals("dlv2") ? SolverType.DLV2 : SolverType.CLINGO);
        ModelHandler modelHandler = (dlvInvocation, modelResult) -> {
            ((Model) modelResult).beforeFirst();
            while (((Model) modelResult).hasMorePredicates()) {
                Predicate predicate = ((Model) modelResult).nextPredicate();
                predicate.beforeFirst();
                while (predicate.hasMoreLiterals()) {
                    Literal literal = predicate.nextLiteral();
                    result.append(literal.toString()).append(",");
                }
            }
            result.deleteCharAt(result.lastIndexOf(","));
            result.append("\n");
            models.add(result.toString());
            result = new StringBuilder("");
        };
        try {
            dlvInvocation.subscribe(modelHandler);
            dlvInvocation.setInputProgram(inputProgram);
            dlvInvocation.addOption("-n " + options.getN());
            dlvInvocation.run();
            dlvInvocation.waitUntilExecutionFinishes();
            return models;
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        } finally {
            this.result = new StringBuilder("");
        }
        return null;
    }


}