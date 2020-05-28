package unical.matteonotaro.aspIdeasServer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASPInput {
    String name;
    String inputProgram;
    String father;
}
