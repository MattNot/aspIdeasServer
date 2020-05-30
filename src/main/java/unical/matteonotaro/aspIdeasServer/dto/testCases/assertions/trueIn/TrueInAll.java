package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@AllArgsConstructor
public class TrueInAll extends TrueIn implements ASPAssertion {
    public boolean check(ArrayList<Model> models) {
        return models.size() == 0;
    }
    @Override
    public String getName() {
        return "TrueInAll";
    }
}
