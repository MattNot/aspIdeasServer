package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.ASPAssertion;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TrueIn implements ASPAssertion {
    String atoms;

    public abstract boolean check(ArrayList<Model> models);

    public String generateTester(String program) {
        atoms = atoms.trim();
        String[] arrayList = atoms.split(Pattern.quote("."));
        StringBuilder builder = new StringBuilder(program);
        for (String atom : arrayList) {
            builder.append("\n:- not ").append(atom).append(".\n");
        }
        return builder.toString();
    }
}
