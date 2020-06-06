package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TrueInAtLeast extends TrueIn implements ASPAssertion {
    Integer number;

    @Override
    public boolean check(ArrayList<Model> models) {
        return models.size() >= number;
    }

    @Override
    public String getName() {
        return "TrueInAtLeast " + number;
    }


    @Override
    public Integer getK() {
        return number;
    }
}