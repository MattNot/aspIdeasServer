package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions.trueIn;

import it.unical.mat.wrapper.Model;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.regex.Pattern;

@AllArgsConstructor
public class TrueInAll extends TrueIn {
    public boolean check(ArrayList<Model> models) {
        return models.size() == 0;
    }

    @Override
    public String getName() {
        return "TrueInAll";
    }

    @Override
    public Integer getK() {
        return 1;
    }

    @Override
    public String generateTester(String program) {
        atoms = atoms.trim();
        String[] arrayList = atoms.split(Pattern.quote("."));
        StringBuilder builder = new StringBuilder(program);
        for (String atom : arrayList) {
            builder.append("\n:- ").append(atom).append(".\n");
        }
        return builder.toString();
    }
}
