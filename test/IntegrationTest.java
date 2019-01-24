import co.com.adnII.dto.CompanyDTO;
import co.com.adnII.model.repository.CompanyRepository;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;

import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.HttpVerbs.PUT;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;


public class IntegrationTest extends WithApplication {

    public static final String URL_API = "/api/v1/companies/";

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void listCompanies() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/api/v1/companies");

        Result result = route(app, request);
        final String body = contentAsString(result);
        JsonNode array=Json.parse(body);
        assertTrue(array.isArray());
        assertTrue(array.size()>0);
        assertEquals(OK, result.status());
    }

    @Test
    public void findCompany() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri(URL_API + "1");

        Result result = route(app, request);
        final String body = contentAsString(result);
        JsonNode object=Json.parse(body);
        assertTrue(object.isObject());
        assertTrue(object.has("id"));
        assertTrue(object.has("nit"));
        assertTrue(object.has("name"));
        assertTrue(object.has("email"));
        assertTrue(object.has("phone_number"));
        assertTrue(object.size()==1);
        assertEquals(OK, result.status());
    }

    @Test
    public void createCompany() {
        JsonNode json = Json.toJson(new CompanyDTO(2l,"900.022.972-4","Empresa SAS","8564545","empresa_info@empresa.com.co"));

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(json)
                .uri(URL_API);

        Result result = route(app, request);
        assertEquals(CREATED, result.status());
    }
}
