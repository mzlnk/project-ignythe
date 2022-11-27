package project.ignythe.shopservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class ApplicationIT extends Specification {

    @Autowired
    ApplicationContext applicationContext

    void "should start application"() {
        expect:
        applicationContext != null
    }

}
