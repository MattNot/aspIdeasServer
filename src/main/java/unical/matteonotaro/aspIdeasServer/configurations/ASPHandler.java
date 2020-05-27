package unical.matteonotaro.aspIdeasServer.configurations;


import it.unical.mat.dlv.program.Literal;
import it.unical.mat.wrapper.*;

import java.io.File;
import java.io.IOException;

public class ASPHandler {
    private static String pathToExe = null;
    private static ASPHandler instance = null;
    private DLVInputProgram inputProgram;
    private DLVInvocation dlvInvocation;

    private ASPHandler() {
        this.setPathToExe();
    }

    private void setPathToExe() {
        if (ASPHandler.pathToExe == null) {
            //Cannot do this on constructor 'cause File(".") doesn't exist, java reasons
            StringBuilder path = new StringBuilder(new File(".").getAbsolutePath());
            path.deleteCharAt(path.indexOf("."));
            pathToExe = path + "src" + File.separator + "main" +
                    File.separator + "resources" + File.separator + "libs" + File.separator + "clingo";
        }
    }

    public static ASPHandler getInstance() {
        if (instance == null) {
            instance = new ASPHandler();
        }
        return instance;
    }

    public Object startGuess(String text) {
        inputProgram = new DLVInputProgramImpl();
        inputProgram.addText(text);
        dlvInvocation = DLVWrapper.getInstance().createInvocation(pathToExe, SolverType.CLINGO);
        ModelHandler modelHandler = (dlvInvocation, modelResult) -> {
            System.out.println("NEXT MODEL: ");
            ((Model) modelResult).beforeFirst();
            while (((Model) modelResult).hasMorePredicates()) {
                Predicate predicate = ((Model) modelResult).nextPredicate();
                predicate.beforeFirst();
                System.out.println(predicate.name());
                while (predicate.hasMoreLiterals()) {
                    Literal literal = predicate.nextLiteral();
                    System.out.println(literal);
                }
            }
        };
        try {

            dlvInvocation.subscribe(modelHandler);
            dlvInvocation.setInputProgram(inputProgram);
            dlvInvocation.run();
            dlvInvocation.waitUntilExecutionFinishes();
        } catch (DLVInvocationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}