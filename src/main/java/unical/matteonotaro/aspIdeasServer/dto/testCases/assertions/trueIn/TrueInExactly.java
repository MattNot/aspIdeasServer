package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

public class TrueInExactly extends TrueInAtLeast {
    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() == number;
    }

    @Override
    public String getName() {
        return "TrueInExactly " + number;
    }

    @Override
    public Integer getK() {
        return super.getK() + 1;
    }
}
