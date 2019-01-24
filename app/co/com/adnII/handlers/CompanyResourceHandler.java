package co.com.adnII.handlers;

import co.com.adnII.dto.CompanyDTO;
import co.com.adnII.model.entities.Company;
import co.com.adnII.model.repository.CompanyRepository;
import com.palominolabs.http.url.UrlBuilder;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;

import javax.inject.Inject;
import java.nio.charset.CharacterCodingException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class CompanyResourceHandler {

    private final CompanyRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public CompanyResourceHandler(CompanyRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<Stream<CompanyDTO>> find() {
        return repository.list().thenApplyAsync(PersonStream -> {
            return PersonStream.map(data -> new CompanyDTO(data, link(data)));
        }, ec.current());
    }

    public CompletionStage<CompanyDTO> create(CompanyDTO resource) {
        final Company data = new Company(resource.getNit(),resource.getName(),resource.getPhoneNumber(), resource.getEmail());
        return repository.create(data).thenApplyAsync(savedData -> {
            return new CompanyDTO(savedData, link(savedData));
        }, ec.current());
    }

    public CompletionStage<Optional<CompanyDTO>> find(String id) {
        return repository.find(Long.parseLong(id)).thenApplyAsync(optionalData -> {
            return optionalData.map(data -> new CompanyDTO(data, link(data)));
        }, ec.current());
    }

    private String link(Company data) {
        // Make a point of using request context here, even if it's a bit strange
        final Http.Request request = Http.Context.current().request();
        final String[] hostPort = request.host().split(":");
        String host = hostPort[0];
        int port = (hostPort.length == 2) ? Integer.parseInt(hostPort[1]) : -1;
        final String scheme = request.secure() ? "https" : "http";
        try {
            return UrlBuilder.forHost(scheme, host, port)
                    .pathSegments("api","v1", "companies", data.getId().toString())
                    .toUrlString();
        } catch (CharacterCodingException e) {
            throw new IllegalStateException(e);
        }
    }

}
