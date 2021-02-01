package kiwi.board.member.model.entity;

import kiwi.board.annotation.Encrypt;
import kiwi.board.common.model.entity.Common;
import kiwi.board.member.model.request.SaveMemberRequest;
import kiwi.board.role.model.entity.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String id;

    @Encrypt
    private String password;

    private String name;

    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_role_mapping", joinColumns = @JoinColumn(name = "memberNo"), inverseJoinColumns = @JoinColumn(name = "roleNo"))
    private List<Role> roles = new ArrayList<>();

    public static Member createMember(SaveMemberRequest saveMemberRequest, Role role) {
        Member member = new Member();
        member.setId(saveMemberRequest.getId());
        member.setPassword(saveMemberRequest.getPassword());
        member.setName(saveMemberRequest.getName());
        member.setEmail(saveMemberRequest.getEmail());

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        member.setRoles(roles);

        return member;
    }
}
