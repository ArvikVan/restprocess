package receiver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ArvikV
 * @version 1.0
 * @since 12.03.2022
 * создаем конфиг, от хмл отказываюсь
 * @ComponentScan("receiver") пакет который надо сканировать
 */
@Configuration
@ComponentScan("receiver")
public class MyConfig {
    /**
     * с помощью этого бина осуществляем хттп реквесты
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
