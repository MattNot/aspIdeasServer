package unical.matteonotaro.aspIdeasServer.utilities;

import it.unical.mat.wrapper.DLVInvocation;
import it.unical.mat.wrapper.Model;
import it.unical.mat.wrapper.ModelHandler;
import it.unical.mat.wrapper.ModelResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
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
