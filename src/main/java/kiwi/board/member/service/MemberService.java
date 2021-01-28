package kiwi.board.member.service;

import kiwi.board.config.Config;
import kiwi.board.config.ConfigRepository;
import kiwi.board.member.model.entity.Member;
import kiwi.board.member.model.request.SaveMemberRequest;
import kiwi.board.member.repository.MemberRepository;
import kiwi.board.role.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ConfigRepository configRepository;

    @Transactional
    public void saveMember(SaveMemberRequest saveMemberRequest) {
        Config config = configRepository.selectConfig();
        Role clientRole = config.getClientRole();

        Member member = Member.createMember(saveMemberRequest, clientRole);
        memberRepository.save(member);
    }
}
