package kiwi.board.domain.config.repository;

import kiwi.board.domain.config.model.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfigRepository extends JpaRepository<Config, Long> {

    @Query("select c from Config c join fetch c.clientRole where c.configNo = 1L")
    Config selectConfig();
}
