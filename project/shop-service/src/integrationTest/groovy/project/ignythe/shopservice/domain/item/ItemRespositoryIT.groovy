package project.ignythe.shopservice.domain.item

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification


@DataJpaTest
class ItemRespositoryIT extends Specification {

    @Autowired
    private ItemRepository itemRepository

    @Autowired
    private TestEntityManager em

    void "should create item"() {
        given:
        def item = new Item(1L, 'apple', 'desc', BigDecimal.ONE, UnitType.KILOGRAM)

        when:
        itemRepository.save(item)

        then:
        def savedItem = em.find(Item, 1L)
        savedItem?.getName() == 'apple'
    }

}
