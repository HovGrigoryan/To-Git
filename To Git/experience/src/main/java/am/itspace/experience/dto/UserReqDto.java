package am.itspace.experience.dto;

import am.itspace.experience.model.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReqDto {

private int id;
private String name;
private String surname;
private String username;
private String password;
private Role role;

}
