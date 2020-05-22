package unical.matteonotaro.aspIdeasServer.configurations;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class DLVHandler {
    private static final String pathToEncodings = "src/main/resources/encodings/";
    private static String pathToExe = null;
    private static DLVHandler instance = null;
    private Handler handler;
    private InputProgram facts;
    private InputProgram encoding;

    private DLVHandler() {
        this.setPathToExe();
        this.handler = new DesktopHandler(new DLV2DesktopService(DLVHandler.pathToExe));
        this.facts = new ASPInputProgram();
        this.encoding = new ASPInputProgram();
    }

    private void setPathToExe() {
        if (DLVHandler.pathToExe == null) {
            //Cannot do this on constructor 'cause File(".") doesn't exist, java reasons
            StringBuilder path = new StringBuilder(new File(".").getAbsolutePath());
            path.deleteCharAt(path.indexOf("."));
            pathToExe = path + File.separator + "src" + File.separator + "main" +
                    File.separator + "resources" + File.separator + "libs" + File.separator + "dlv2";
        }
    }


    public static DLVHandler getInstance() {
        if (instance == null) {
            instance = new DLVHandler();
        }
        return instance;
    }

    public List<AnswerSet> startGuess(Collection<Object> facts, String encoding) {
        this.setEncoding(encoding);
        for (Object obj : facts)
            this.addFact(obj);
        this.addFactsToHandler();
        return this.getAnswerSets();
    }

    private void setEncoding(String path) {
        this.facts.clearAll();
        this.handler.removeAll();
        this.encoding.clearAll();
        this.encoding.addProgram(path);
        this.handler.addProgram(this.encoding);
    }

    private void addFact(Object object) {
        try {
            this.facts.addObjectInput(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFactsToHandler() {
        this.handler.addProgram(this.facts);
    }

    private List<AnswerSet> getAnswerSets() {
        Output o = this.handler.startSync();
        AnswerSets answerSets = (AnswerSets) o;
        return answerSets.getAnswersets();
    }

}