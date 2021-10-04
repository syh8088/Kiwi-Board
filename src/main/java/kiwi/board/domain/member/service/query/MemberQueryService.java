package kiwi.board.domain.member.service.query;

import kiwi.board.common.utils.BeanUtils;
import kiwi.board.common.utils.JacksonUtils;
import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.member.model.response.MemberResponse;
import kiwi.board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Member selectMemberById(String username) {
        return memberRepository.findByIdAndUseYn(username, true);
    }

    public boolean isAlreadyRegisteredId(String id) {
        return memberRepository.findById(id) != null;
    }

    public boolean isAlreadyRegisteredEmail(String email) {
        return memberRepository.findByEmail(email) != null;
    }

    public MemberResponse getMe(Long memberNo) {
        Member member = memberRepository.getOne(memberNo);
        return BeanUtils.copyProperties(member, MemberResponse.class);
    }

    public byte[] getFile() throws IOException {

        Member member = memberRepository.findById("syh8088");
        String json = JacksonUtils.toForceJson(member);

        System.out.println("json = " + json);

  /*      String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter("test"));
        writer.write(json);
        writer.close();*/

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
        writer.write(json);
        writer.close();
        byte[] bytes = baos.toByteArray();

        return bytes;
    }
}
