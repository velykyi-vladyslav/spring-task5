package velykyi.vladyslav.task4.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User  {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
