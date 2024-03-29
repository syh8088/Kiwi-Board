package kiwi.board.domain.resources.repository;

import kiwi.board.domain.resources.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("select r from Resource r join fetch r.roleSet where r.type = 'url' and r.useYn = true order by r.orderNum desc")
    List<Resource> findAllResources();
}
