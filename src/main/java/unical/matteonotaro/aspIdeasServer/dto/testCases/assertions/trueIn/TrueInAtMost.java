package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TrueInAtMost extends TrueInAtLeast implements ASPAssertion {
    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() <= num;
    }

    @Override
    public String getName() {
        return "TrueInAtMost " + num;
    }
}
