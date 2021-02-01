package kiwi.board.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kiwi.board.member.model.request.SaveMemberRequest;
import kiwi.board.member.service.MemberService;
import kiwi.board.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Member", description = "회원 본문")
@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;
    private final Validator validator;

    @PostMapping
    @ApiOperation(value = "회원가입", notes = "회원가입을 합니다. ")
    public ResponseEntity<?> saveMember(@RequestBody SaveMemberRequest saveMemberRequest) {

        validator.saveMember(saveMemberRequest);

        memberService.saveMember(saveMemberRequest);
        return ResponseEntity.noContent().build();
    }
}
