package com.cwm.student_rest.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentDTO {

    private Long id;

    @Size(max = 255)
    private String firstname;

    @Size(max = 255)
    private String lastname;

    @Size(max = 255)
    @StudentEmailUnique
    private String email;

}
