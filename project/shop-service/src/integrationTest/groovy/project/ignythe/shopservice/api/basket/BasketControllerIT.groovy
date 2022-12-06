package project.ignythe.shopservice.api.basket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import project.ignythe.shopservice.api.ApiTestConfiguration
import project.ignythe.shopservice.domain.basket.Basket
import project.ignythe.shopservice.domain.basket.BasketNotFoundException
import project.ignythe.shopservice.domain.basket.BasketStorage
import project.ignythe.shopservice.domain.item.ItemStorage
import spock.lang.Specification

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest
@Import(ApiTestConfiguration)
class BasketControllerIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    BasketStorage basketStorage

    @MockBean
    ItemStorage itemService

    private BasketControllerFixture fixture

    void setup() {
        fixture = new BasketControllerFixture(mockMvc: mockMvc)
    }

    void "should get basket by ID"() {
        given:
        def basketId = 123L

        and:
        when(basketStorage.getById(basketId)).thenReturn(new Basket(basketId, 'name', [] as Set))

        when:
        def result = fixture.readBasketById(basketId)

        then:
        result == ""
    }

    void "should get HTTP 400 when basket not found"() {
        given:
        def basketId = 123L

        and:
        when(basketStorage.getById(basketId)).thenThrow(new BasketNotFoundException(basketId))

        when:
        def result = fixture.readBasketById(basketId)

        then:
        result == ""
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
