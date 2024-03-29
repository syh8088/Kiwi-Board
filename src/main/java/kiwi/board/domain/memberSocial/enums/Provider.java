package kiwi.board.domain.memberSocial.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Provider {

    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver"),
    NONE("none");

    private final String provider;

    Provider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return this.provider;
    }

    public static Provider getByProvider(String provider) {
        return Arrays.stream(Provider.values())
                .filter(data -> data.getProvider().equals(provider))
                .findFirst()
                .orElse(Provider.NONE);
    }
}
