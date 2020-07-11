package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.constraintIn;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

@Data
@Slf4j
public abstract class ConstraintIn implements ASPAssertion {
    public String constraint;

    @Override
    public String generateTester(String program) {
        log.error(program + "\n" + constraint);
        return program + ("\n" + constraint + "\n");
    }
}
