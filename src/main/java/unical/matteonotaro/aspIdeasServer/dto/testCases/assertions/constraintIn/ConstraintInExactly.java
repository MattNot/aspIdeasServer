package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import it.unical.mat.wrapper.Model;

import java.util.ArrayList;

public class ConstraintInExactly extends ConstraintInAtLeast {
    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() == this.number;
    }

    @Override
    public Integer getK() {
        return super.getK();
    }

    @Override
    public String getName() {
        return "ConstraintInExactly " + this.number;
    }
}
