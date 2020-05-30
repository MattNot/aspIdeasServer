package unical.matteonotaro.aspIdeasServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.configurations.ASPOptions;
import unical.matteonotaro.aspIdeasServer.dto.testCases.ASPTestCase;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestWrapper {
    private ArrayList<ASPTestCase> testCases;
    private ASPOptions options;
}
