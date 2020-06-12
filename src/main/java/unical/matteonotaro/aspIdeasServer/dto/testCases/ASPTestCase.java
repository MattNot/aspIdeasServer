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
    private String name;
    private ArrayList<Block> scope;
    private ArrayList<String> programFiles = new ArrayList<>();
    private String input;
    private ArrayList<ASPAssertion> assertions = new ArrayList<>();
    public String getProgram() {
        StringBuilder builder = new StringBuilder();
        for (Block block : scope) {
            for (String rule : block.getRules())
                builder.append(rule);
        }
        builder.append(input);
        return builder.toString();
    }
}
