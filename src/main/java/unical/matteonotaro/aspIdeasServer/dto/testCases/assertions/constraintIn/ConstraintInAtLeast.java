package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintInAtLeast extends ConstraintIn {
    public Integer number;

    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() >= number;
    }

    @Override
    public Integer getK() {
        return number + 1;
    }

    @Override
    public String getName() {
        return "ConstraintInAtLeast " + number;
    }
}
