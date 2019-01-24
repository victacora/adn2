package co.com.adnII.controllers;

import co.com.adnII.dto.CompanyDTO;
import co.com.adnII.handlers.CompanyResourceHandler;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class CompanyController extends Controller {

    private HttpExecutionContext ec;
    private CompanyResourceHandler handler;

    @Inject
    public CompanyController(HttpExecutionContext ec, CompanyResourceHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> list() {
        return handler.find().thenApplyAsync(posts -> {
            final List<CompanyDTO> postList = posts.collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }

    public CompletionStage<Result> find(String id) {
        return handler.find(id).thenApplyAsync(optionalResource -> {
            return optionalResource.map(resource ->
                    ok(Json.toJson(resource))
            ).orElseGet(() ->
                    notFound()
            );
        }, ec.current());
    }


    public CompletionStage<Result> create() {
        JsonNode json = request().body().asJson();
        final CompanyDTO resource = Json.fromJson(json, CompanyDTO.class);
        return handler.create(resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
        }, ec.current());
    }


}
