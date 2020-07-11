package unical.matteonotaro.aspIdeasServer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import unical.matteonotaro.aspIdeasServer.utilities.ASPHandler;

@RestController
@Slf4j
public class OptionsController {
    @GetMapping(value = "/option")
    public boolean testOption(String option, String engine) {
        return ASPHandler.getInstance().checkOption(option, engine);
    }
}
