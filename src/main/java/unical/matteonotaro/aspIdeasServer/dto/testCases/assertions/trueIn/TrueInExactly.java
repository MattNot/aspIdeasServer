package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

public class TrueInExactly extends TrueInAtLeast implements ASPAssertion {
    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() == num;
    }

    @Override
    public String getName() {
        return "TrueInExectly " + num;
    }
}