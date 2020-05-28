package unical.matteonotaro.aspIdeasServer.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASPOptions {
    private boolean test = false;
    private int n = 1;
    private String executor = "dlv2";
}
