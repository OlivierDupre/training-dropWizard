package fr.training.dropwizard;

/**
 * @author shuttle
 */
import com.yammer.dropwizard.config.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String template;
    @NotEmpty
    @JsonProperty
    private String defaultName = "Olivier";

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
