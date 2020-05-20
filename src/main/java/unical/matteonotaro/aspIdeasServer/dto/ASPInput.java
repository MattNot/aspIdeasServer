package unical.matteonotaro.aspIdeasServer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASPInput {
    String name;
    String inputProgram;
    ArrayList<ASPTestCase> testCases;
}
