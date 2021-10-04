package kiwi.board.domain.role.model.entity;

import kiwi.board.common.model.entity.Common;
import kiwi.board.domain.memberRoleMapping.model.entity.MemberRoleMapping;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleNo;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<MemberRoleMapping> memberRoleMappings = new ArrayList<>();
}
