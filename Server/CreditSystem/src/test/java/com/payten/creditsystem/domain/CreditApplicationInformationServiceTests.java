package com.payten.creditsystem.domain;

import com.payten.creditsystem.BaseIntegrationTest;
import com.payten.creditsystem.adapter.redis.CreditApplicationInformationCache;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

public class CreditApplicationInformationServiceTests extends BaseIntegrationTest {

    @Autowired
    CreditApplicationInformationService creditApplicationInformationService;

    @Autowired
    RedisTemplate<String, CreditApplicationInformationCache> creditApplicationInformationCacheRedisTemplate;

    @Test
    @Sql(scripts = "/credit-application-information-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_credit_application_information_by_id() {
        // given
        // via Sql Annotation

        // when
        CreditApplicationInformation response = creditApplicationInformationService.retrieve(1001L);

        // then
        assertThat(response).isNotNull();

        //validate-cache
        CreditApplicationInformationCache creditApplicationInformation = creditApplicationInformationCacheRedisTemplate.opsForValue().get("payten:credit-application-information:" + 1001);
        assertThat(creditApplicationInformation).isNotNull();
        assertThat(creditApplicationInformation.getCreditLimit()).isEqualTo(300000L);
        //todo validate other fields
    }
}
