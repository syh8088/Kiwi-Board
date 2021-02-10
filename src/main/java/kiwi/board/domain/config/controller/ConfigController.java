package kiwi.board.domain.config.controller;

import kiwi.board.domain.config.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/configs")
public class ConfigController {

    private final ConfigService configService;

}
