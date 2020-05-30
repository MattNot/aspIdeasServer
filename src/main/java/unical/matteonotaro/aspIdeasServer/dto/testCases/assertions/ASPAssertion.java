package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unical.mat.wrapper.Model;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn.*;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes(
        {
            @JsonSubTypes.Type(value = TrueInAll.class, name = "TrueInAll"),
            @JsonSubTypes.Type(value = TrueInAtLeast.class, name = "TrueInAtLeast"),
            @JsonSubTypes.Type(value = TrueInAtMost.class, name = "TrueInAtMost"),
            @JsonSubTypes.Type(value = TrueInExactly.class, name = "TrueInExactly"),
            @JsonSubTypes.Type(value = NoAnswerSet.class, name = "NoAnswerSets"),
            @JsonSubTypes.Type(value = BestModelCost.class, name = "BestModelCost"),
        }
)
public interface ASPAssertion {
    boolean check(ArrayList<Model> models);

    String generateTester(String program);

    String getName();
}
