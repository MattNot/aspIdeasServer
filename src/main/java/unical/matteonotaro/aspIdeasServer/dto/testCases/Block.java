package unical.matteonotaro.aspIdeasServer.dto.testCases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private String name;
    private ArrayList<String> rules;
}
