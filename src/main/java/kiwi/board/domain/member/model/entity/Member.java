package kiwi.board.domain.member.model.entity;

import kiwi.board.annotation.Encrypt;
import kiwi.board.domain.member.enums.MemberType;
import kiwi.board.common.model.entity.Common;
import kiwi.board.domain.member.model.request.SaveMemberRequest;
import kiwi.board.domain.memberRoleMapping.model.entity.MemberRoleMapping;
import kiwi.board.domain.memberSocial.model.entity.MemberSocial;
import kiwi.board.domain.role.model.entity.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

/*    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_role_mapping", joinColumns = @JoinColumn(name = "memberNo"), inverseJoinColumns = @JoinColumn(name = "roleNo"))
    private List<Role> roles = new ArrayList<>();*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberRoleMapping> memberRoleMappings = new ArrayList<>();

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_SOCIAL_NO")
    private MemberSocial memberSocial;

    public static Member createMember(SaveMemberRequest saveMemberRequest, Role role) {

        Member member = new Member();

        member.setId(saveMemberRequest.getId());
        member.setPassword(saveMemberRequest.getPassword());
        member.setName(saveMemberRequest.getName());
        member.setEmail(saveMemberRequest.getEmail());

        MemberRoleMapping memberRoleMapping = MemberRoleMapping.builder()
                .member(member)
                .role(role)
                .build();

        List<MemberRoleMapping> memberRoleMappings = new ArrayList<>();
        memberRoleMappings.add(memberRoleMapping);

        member.setMemberRoleMappings(memberRoleMappings);

/*        List<Role> roles = new ArrayList<>();
        roles.add(role);
        member.setRoles(roles);*/

        return member;
    }

/*    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }*/

    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.memberRoleMappings.stream().map(memberRoleMapping -> new SimpleGrantedAuthority(memberRoleMapping.getRole().getName())).collect(Collectors.toList());
    }
}
