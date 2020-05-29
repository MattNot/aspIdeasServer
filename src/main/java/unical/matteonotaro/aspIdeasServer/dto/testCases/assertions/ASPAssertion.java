package unical.matteonotaro.aspIdeasServer.dto.testCases.assertions;

import it.unical.mat.wrapper.Model;

import java.util.ArrayList;

public interface ASPAssertion {
    boolean check(ArrayList<Model> models);

    String generateTester(String program);

    String getName();
}
