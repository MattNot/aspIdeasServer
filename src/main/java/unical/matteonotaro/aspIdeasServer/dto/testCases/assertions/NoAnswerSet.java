package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class NoAnswerSet implements ASPAssertion {
    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() == 0;
    }

    @Override
    public String generateTester(String program) {
        return program;
    }

    @Override
    public String getName() {
        return "NoAnswerSet";
    }

    @Override
    public Integer getK() {
        return 1;
    }
}
