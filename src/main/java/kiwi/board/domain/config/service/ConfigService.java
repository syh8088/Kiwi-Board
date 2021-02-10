package kiwi.board.domain.config.service;

import kiwi.board.domain.config.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigRepository configRepository;
}
