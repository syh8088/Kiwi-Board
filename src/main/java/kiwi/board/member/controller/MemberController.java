package kiwi.board.member.controller;

import kiwi.board.member.model.request.SaveMemberRequest;
import kiwi.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<?> saveMember(@RequestBody SaveMemberRequest saveMemberRequest) {

        memberService.saveMember(saveMemberRequest);
        return ResponseEntity.noContent().build();
    }
}
