package org.emnmem.oblig2.model;

import lombok.*;
import org.emnmem.oblig2.dto.UserDto;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

}
