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
    ArrayList<Model> models = new ArrayList<>();
    ArrayList<String> outputModels = new ArrayList<>();
    private StringBuilder result = new StringBuilder("");

    @Override
    public void handleResult(DLVInvocation dlvInvocation, ModelResult modelResult) {
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
        models.add((Model) modelResult);
        ((Model) modelResult).costAndPriority.forEach((key, value) -> result.append(value).append("@").append(key).append("\n"));
        outputModels.add(result.toString());
        result = new StringBuilder("");
    }

    public void cleanup() {
        models.clear();
        outputModels.clear();
        result = new StringBuilder();
    }
}
