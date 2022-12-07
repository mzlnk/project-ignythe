package project.ignythe.shopservice.api.basket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import project.ignythe.shopservice.api.IncludeApiConfiguration
import project.ignythe.shopservice.domain.basket.Basket
import project.ignythe.shopservice.domain.basket.BasketNotFoundException
import project.ignythe.shopservice.domain.basket.BasketStorage
import project.ignythe.shopservice.domain.item.ItemStorage
import project.ignythe.shopservice.domain.order.OrderStorage
import spock.lang.Specification

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static project.ignythe.shopservice.domain.basket.BasketOperations.BasketGetDetails

@WebMvcTest
@IncludeApiConfiguration
class BasketControllerIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    BasketStorage basketStorage

    @MockBean
    ItemStorage itemService

    @MockBean
    OrderStorage orderStorage

    private BasketControllerFixture fixture

    void setup() {
        fixture = new BasketControllerFixture(mockMvc: mockMvc)
    }

    void "should get basket by ID"() {
        given:
        def basketId = 123L
        def getDetails = new BasketGetDetails(basketId)

        and:
        when(basketStorage.getBasketById(getDetails)).thenReturn(new Basket(basketId, 'name', [] as Set))

        when:
        def result = fixture.readBasketById(basketId)

        then:
        result == """{"id":123,"name":"name","items":[]}"""
    }

    void "should get HTTP 400 when basket not found"() {
        given:
        def basketId = 123L
        def getDetails = new BasketGetDetails(basketId)

        and:
        when(basketStorage.getBasketById(getDetails)).thenThrow(new BasketNotFoundException(basketId))

        when:
        def result = fixture.readBasketById(basketId)

        then:
        result == """{"title":"Basket Not Found","detail":"Basket with id '123' not found"}"""
    }

    private static class BasketControllerFixture {

        private MockMvc mockMvc

        def readBasketById(Long id) {
            return mockMvc.perform(get("/baskets/$id").contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse()
                    .getContentAsString()
        }

    }

}
