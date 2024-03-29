package kiwi.board.domain.role.repository;

import com.querydsl.core.BooleanBuilder;
import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.member.model.entity.QMember;
import kiwi.board.domain.memberRoleMapping.model.entity.QMemberRoleMapping;
import kiwi.board.domain.role.model.entity.QRole;
import kiwi.board.domain.role.model.entity.Role;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class RoleRepositoryImpl extends QuerydslRepositorySupport implements RoleRepositoryCustom {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */

    QRole qRole = QRole.role;
    QMemberRoleMapping qMemberRoleMapping = QMemberRoleMapping.memberRoleMapping;
    QMember qMember = QMember.member;

    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public List<Role> selectAllRolesByMember(Member member) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qMemberRoleMapping.member.eq(member));
        booleanBuilder.and(qRole.useYn.eq(true));

        return from(qRole)
                .innerJoin(qRole.memberRoleMappings, qMemberRoleMapping)
                .innerJoin(qMemberRoleMapping.member, qMember)
                .where(booleanBuilder)
                .fetch();
    }
}
