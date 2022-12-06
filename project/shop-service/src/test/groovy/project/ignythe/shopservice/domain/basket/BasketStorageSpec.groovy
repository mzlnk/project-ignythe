package project.ignythe.shopservice.domain.basket


import project.ignythe.shopservice.domain.item.ItemStorage
import spock.lang.Specification

class BasketStorageSpec extends Specification {

    private BasketRepository basketRepository = Mock()
    private BasketItemRepository basketItemRepository = Mock()
    private ItemStorage itemService = Mock()

    private BasketStorage basketStorage = new BasketStorage(basketRepository, basketItemRepository, itemService)

    void "should get basket by ID"() {
        given:
        def basketId = 10L

        and:
        basketRepository.findById(basketId) >> Optional.of(new Basket(basketId, 'someName', [] as Set))
        basketRepository.findById(_) >> Optional.empty()

        when:
        def basket = basketStorage.getById(basketId)

        then:
        basket != null
        basket.getId() == basketId
        basket.getName() == 'someName'
    }

    void "should throw exception when get non-existing basket"() {
        given:
        def basketId = 10L

        and:
        basketRepository.findById(basketId) >> Optional.empty()

        when:
        basketStorage.getById(basketId)

        then:
        def exception = thrown(BasketNotFoundException)
        exception.message == "Basket with id '$basketId' not found"
    }

}
