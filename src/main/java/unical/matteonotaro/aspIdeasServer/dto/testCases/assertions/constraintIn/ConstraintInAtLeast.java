package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class ConstraintInAtLeast extends ConstraintForAll implements ASPAssertion {
    private Integer number;

    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() >= number;
    }
}
