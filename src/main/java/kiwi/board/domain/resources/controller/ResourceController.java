package kiwi.board.domain.resources.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Resource", description = "인가 자원 실시간 반영하기")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/resources")
public class ResourceController {


}
