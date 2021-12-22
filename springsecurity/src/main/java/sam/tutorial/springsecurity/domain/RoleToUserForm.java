package sam.tutorial.springsecurity.domain;

import lombok.Data;

@Data
public class RoleToUserForm {
    private String userName;
    private String roleName;
}
