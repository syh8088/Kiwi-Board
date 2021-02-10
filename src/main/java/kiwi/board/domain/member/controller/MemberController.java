package kiwi.board.domain.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kiwi.board.annotation.LoginUser;
import kiwi.board.common.model.request.JwtUserRequest;
import kiwi.board.domain.member.model.request.SaveMemberRequest;
import kiwi.board.domain.member.model.response.MemberResponse;
import kiwi.board.domain.member.service.MemberService;
import kiwi.board.domain.member.service.query.MemberQueryService;
import kiwi.board.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Api(tags = "Member", description = "회원 본문")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberService memberService;
    private final Validator validator;

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

        MemberResponse memberResponse = memberQueryService.getMe(jwtUserRequest.getMember_seq());
        return (memberResponse == null) ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(memberResponse);
    }

    @GetMapping("file/test")
    @ApiOperation(value = "파일 다운 TEST", notes = "파일 다운 TEST 합니다.")
    public ResponseEntity<?> getFile() throws IOException {
        byte[] file = memberQueryService.getFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.srt")
                .body(new ByteArrayResource(file));
    }


}
