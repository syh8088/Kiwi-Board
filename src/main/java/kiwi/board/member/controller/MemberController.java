package kiwi.board.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kiwi.board.annotation.LoginUser;
import kiwi.board.common.config.authentication.JwtTokenProvider;
import kiwi.board.common.model.request.JwtUserRequest;
import kiwi.board.member.model.request.SaveMemberRequest;
import kiwi.board.member.service.MemberService;
import kiwi.board.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Member", description = "회원 본문")
@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;
    private final Validator validator;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    @ApiOperation(value = "회원가입", notes = "회원가입을 합니다.")
    public ResponseEntity<?> saveMember(@RequestBody SaveMemberRequest saveMemberRequest) {

        validator.saveMember(saveMemberRequest);

        memberService.saveMember(saveMemberRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("me")
    @ApiOperation(value = "회원 정보", notes = "회원 정보를 출력 합니다.")
    public ResponseEntity<?> getMe(@LoginUser JwtUserRequest jwtUserRequest) {


        System.out.println("jwtUserRequest = " + jwtUserRequest);
        return ResponseEntity.noContent().build();
    }
}
