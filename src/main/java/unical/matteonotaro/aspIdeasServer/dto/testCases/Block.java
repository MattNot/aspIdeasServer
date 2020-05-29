package unical.matteonotaro.aspIdeasServer.dto.testCases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    String name;
    ArrayList<String> rules;
}
