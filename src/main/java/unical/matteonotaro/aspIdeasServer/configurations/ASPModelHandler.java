package unical.matteonotaro.aspIdeasServer.configurations;

import it.unical.mat.dlv.program.Literal;
import it.unical.mat.wrapper.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ASPModelHandler implements ModelHandler {
    private ArrayList<Model> models = new ArrayList<>();
    private StringBuilder result = new StringBuilder("");

    @Override
    public void handleResult(DLVInvocation dlvInvocation, ModelResult modelResult) {
        models.add((Model) modelResult);
    }

    public ArrayList<String> getResults(){
        ArrayList<String> results = new ArrayList<>();
        for (Model m:models){
            StringBuilder cost = new StringBuilder();
            m.costAndPriority.forEach((key, value) -> cost.append(value).append("@").append(key).append("\n"));
            results.add(m.toString() + "\n"+ cost);
        }
        return results;
    }

    public void cleanup() {
        models.clear();
        result = new StringBuilder();
    }
}
