package unical.matteonotaro.aspIdeasServer.configurations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ASPOptions {
    private ArrayList<String> cliOptions = new ArrayList<>();
    private String executor = "dlv2";
}
