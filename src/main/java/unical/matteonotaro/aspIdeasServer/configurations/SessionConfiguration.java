package unical.matteonotaro.aspIdeasServer.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

import java.time.Duration;

@EnableMongoHttpSession
public class SessionConfiguration {
        @Bean
        public JdkMongoSessionConverter jdkMongoSessionConverter() {
            return new JdkMongoSessionConverter(Duration.ofDays(1));
        }
}
