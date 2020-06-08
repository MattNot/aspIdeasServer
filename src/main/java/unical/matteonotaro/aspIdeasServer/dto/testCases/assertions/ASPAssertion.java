package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unical.mat.wrapper.Model;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn.ConstraintForAll;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn.ConstraintInAtLeast;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn.TrueInAll;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn.TrueInAtLeast;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn.TrueInAtMost;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn.TrueInExactly;

import java.util.ArrayList;

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = TrueInAll.class, name = "trueInAll"),
                @JsonSubTypes.Type(value = TrueInAtLeast.class, name = "trueInAtLeast"),
                @JsonSubTypes.Type(value = TrueInAtMost.class, name = "trueInAtMost"),
                @JsonSubTypes.Type(value = TrueInExactly.class, name = "trueInExactly"),
                @JsonSubTypes.Type(value = NoAnswerSet.class, name = "noAnswerSets"),
                @JsonSubTypes.Type(value = BestModelCost.class, name = "testModelCost"),
                @JsonSubTypes.Type(value = ConstraintForAll.class, name = "constraintForAll"),
                @JsonSubTypes.Type(value = ConstraintInAtLeast.class, name = "constraintInAtLeast")
        }
)
public interface ASPAssertion {
    boolean check(ArrayList<Model> models);

    String generateTester(String program);

    Integer getK();

    String getName();
}
