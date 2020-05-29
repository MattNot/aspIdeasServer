package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestModelCost implements ASPAssertion {
    private Integer cost;
    private Integer level;

    @Override
    public boolean check(ArrayList<Model> models) {
        for (Model model : models)
            if (model.isBest())
                return model.getCost(level) == cost;
        return false;
    }

    @Override
    public String generateTester(String program) {
        return program;
    }

    @Override
    public String getName() {
        return "BestModelCost " + cost + "@" + level;
    }
}
