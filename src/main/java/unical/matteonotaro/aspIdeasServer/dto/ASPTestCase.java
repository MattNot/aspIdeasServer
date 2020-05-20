package unical.matteonotaro.aspIdeasServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ASPTestCase {
    String name;
    String input;
    String expected;
    public boolean isCorrect(String result){
        return expected.equalsIgnoreCase(result);
    }
}
