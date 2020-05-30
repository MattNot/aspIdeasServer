package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrueInAtLeast extends TrueIn implements ASPAssertion {
    Integer num;

    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() >= num;
    }

    @Override
    public String getName() {
        return "TrueInAtLeast " + num;
    }
}