package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import lombok.Data;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

@Data
public abstract class ConstraintIn implements ASPAssertion {
    public String constraint;

    @Override
    public String generateTester(String program) {
        return program + ("\n" + constraint + "\n");
    }
}
