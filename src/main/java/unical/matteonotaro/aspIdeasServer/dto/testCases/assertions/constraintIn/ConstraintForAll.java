package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class ConstraintForAll implements ASPAssertion {

    private String constraint;

    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() != 0;
    }

    @Override
    public String generateTester(String program) {
        return program + ("\n" + constraint + "\n");
    }

    @Override
    public Integer getK() {
        return 1;
    }

    @Override
    public String getName() {
        return "ConstraintForAll";
    }
}
