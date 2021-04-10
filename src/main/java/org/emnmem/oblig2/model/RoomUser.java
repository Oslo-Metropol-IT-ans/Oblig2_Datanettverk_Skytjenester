package org.emnmem.oblig2.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roomuser")
public class RoomUser implements Serializable {

    @EmbeddedId
    private RoomUserId roomUserId;

}
