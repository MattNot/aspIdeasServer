package unical.matteonotaro.aspIdeasServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unical.matteonotaro.aspIdeasServer.configurations.ASPOptions;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateWrapper {
    private ASPInput input;
    private ASPOptions options;
}
