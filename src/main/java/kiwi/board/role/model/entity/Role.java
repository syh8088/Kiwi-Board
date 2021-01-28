package kiwi.board.role.model.entity;

import kiwi.board.common.model.entity.Common;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "role")
public class Role extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleNo;

    @Column(nullable = false)
    private String name;
}
