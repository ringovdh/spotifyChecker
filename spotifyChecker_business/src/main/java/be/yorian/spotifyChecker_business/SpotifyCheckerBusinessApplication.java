package be.yorian.spotifyChecker_business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"be.yorian"})
@EnableJpaRepositories("be.yorian.spotifyChecker_repository.repository")
@EntityScan("be.yorian.spotifyChecker_repository.model")
@PropertySource(value= "classpath:spotify.properties")
public class SpotifyCheckerBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyCheckerBusinessApplication.class, args);
    }

}
