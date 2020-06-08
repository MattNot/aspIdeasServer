package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
public class ConstraintForAll extends ConstraintIn {
    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() == 0;
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
