package unical.matteonotaro.aspIdeasServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.configurations.ASPOptions;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateWrapper {
    private ASPInput input;
    private ArrayList<String> testCases;
    private ASPOptions options;
}
