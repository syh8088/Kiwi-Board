package kiwi.board.domain.member.service;

import kiwi.board.domain.config.model.entity.Config;
import kiwi.board.domain.config.repository.ConfigRepository;
import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.member.model.request.SaveMemberRequest;
import kiwi.board.domain.member.repository.MemberRepository;
import kiwi.board.domain.role.model.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ConfigRepository configRepository;

    public void saveMember(SaveMemberRequest saveMemberRequest) {
        Config config = configRepository.selectConfig();
        Role clientRole = config.getClientRole();

        Member member = Member.createMember(saveMemberRequest, clientRole);
        memberRepository.save(member);
    }


}
