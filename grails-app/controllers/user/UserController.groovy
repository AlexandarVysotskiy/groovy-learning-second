package user

import com.second.UsersDto
import grails.transaction.Transactional
import groovyx.net.http.HTTPBuilder
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.context.i18n.LocaleContextHolder
import seventh.User

import java.text.SimpleDateFormat

import static groovyx.net.http.Method.GET

@Transactional(readOnly = true)
class UserController {

    def index(String userName, String pokemonName, String from, String to) {
        UsersDto dto

        if (userName && pokemonName) {
            dto = requestData(userName, pokemonName, params)
        } else {
            dto = requestData('*', '*', params)
        }

        render view: "index", model: [users : dto.users, max: dto.max, offser: dto.offset, userCount: dto.total]
    }

    def search(String userName, String pokemonName, String from, String to) {
        UsersDto dto

        if (userName && pokemonName) {
            dto = requestData(userName, pokemonName, params)
        } else {
            dto = requestData('*', '*', params)
        }

        render view: "index", model: [users : dto.users, max: dto.max, offser: dto.offset, userCount: dto.total]
    }

    UsersDto requestData(String userName, String pokemonName, GrailsParameterMap params) {
        UsersDto result = new UsersDto()
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", LocaleContextHolder.getLocale())
        List<User> users = new ArrayList()

        def http = new HTTPBuilder("http://localhost:8090/grails-com.learning/userRest/search?" +
                "userName=${userName}&pokemonName=${pokemonName}&max=${params.max}&offset=${params.offset}")
        http.request(GET, 'application/json') { req ->

            response.success = { resp, json ->
                println resp.statusLine
                String headerParams = resp.headers.'Content-Range'

                result.offset = headerParams[6]
                result.max = headerParams[8]
                result.total = headerParams.split('/')[1]

                json.each {
                    users.add(new User(it.name, it.password, formatter.parse(it.birthday)))
                }

                result.users = users
            }

            response.failure = { resp ->
                println 'not found!'
            }
        }

        result
    }
}
