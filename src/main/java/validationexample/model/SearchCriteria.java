package validationexample.model;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "{username_cant_be_empty}")
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}