package unical.matteonotaro.aspIdeasServer.dto.testCases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ASPTestCase {
    String name;
    ArrayList<Block> scope;
    ArrayList<String> programFiles = new ArrayList<>();
    String input;
    ArrayList<ASPAssertion> assertions = new ArrayList<>();

    public String getProgram() {
        StringBuilder builder = new StringBuilder();
        for (Block block : scope) {
            for (String rule : block.rules)
                builder.append(rule);
        }
        for (String program : programFiles) {
            builder.append(program);
        }
        return builder.toString();
    }
}
