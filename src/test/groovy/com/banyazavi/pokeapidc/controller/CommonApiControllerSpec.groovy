package com.banyazavi.pokeapidc.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CommonApiController.class)
class CommonApiControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    def "Response status for Common APIs"() {
        expect:
        mvc.perform(get(url)).andExpect(statusMatcher)

        where:
        url             || statusMatcher
        "/health-check" || status().isOk()
        "/invalid-uri"  || status().isNotFound()
    }
}
