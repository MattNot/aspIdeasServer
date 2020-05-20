package unical.matteonotaro.aspIdeasServer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Project {
    @Id
    private String id;
    private String name;
    private ArrayList<ASPInput> programs;
}
