package kiwi.board.config;

import kiwi.board.common.model.entity.CommonExclusionOfUseYn;
import kiwi.board.role.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Config extends CommonExclusionOfUseYn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long configNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_role_no")
    private Role clientRole;
}
