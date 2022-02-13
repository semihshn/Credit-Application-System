package com.payten.creditsystem.adapter.redis;

import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.port.CreditApplicationInformationCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisAdapter implements CreditApplicationInformationCachePort {

    private final RedisTemplate<String, CreditApplicationInformationCache> redisTemplate;

    @Override
    public Optional<CreditApplicationInformation> retrieveCreditApplicationInformation(Long creditApplicationInformationId) {
        CreditApplicationInformationCache creditApplicationInformationCache = redisTemplate.opsForValue().get("payten:credit-application-information:" + creditApplicationInformationId);

        return Optional.ofNullable(creditApplicationInformationCache)
                .map(CreditApplicationInformationCache::toModel);
    }

    @Override
    public void createCreditApplicationInformation(CreditApplicationInformation creditApplicationInformation) {
        CreditApplicationInformationCache creditApplicationInformationCache = CreditApplicationInformationCache.from(creditApplicationInformation);
        redisTemplate.opsForValue().set("payten:credit-application-information:" + creditApplicationInformation.getId(), creditApplicationInformationCache, Duration.ofSeconds(30));
    }
}
