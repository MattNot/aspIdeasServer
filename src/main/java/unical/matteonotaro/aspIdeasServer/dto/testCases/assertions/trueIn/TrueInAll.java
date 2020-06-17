package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class TrueInAll extends TrueIn {
    public boolean check(ArrayList<Model> models) {
        return models.size() == 0;
    }

    @Override
    public String getName() {
        return "TrueInAll";
    }

    @Override
    public Integer getK() {
        return 1;
    }
}
