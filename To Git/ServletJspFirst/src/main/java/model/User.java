package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserType userType;
    private Gender gender;
    private Lesson lesson;
    private Date creationDate;
}
