package co.com.adnII.application.controllers;

import co.com.adnII.application.dto.command.CompanyCreateCommandDTO;
import co.com.adnII.application.dto.query.CompanyListQueryDTO;
import co.com.adnII.application.service.command.ICompanyCommandService;
import co.com.adnII.application.service.query.ICompanyQueryService;
import co.com.adnII.domain.entities.Company;
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
    private ICompanyQueryService iCompanyQueryService;
    private ICompanyCommandService iCompanyCommandService;

    @Inject
    public CompanyController(HttpExecutionContext ec, ICompanyQueryService iCompanyQueryService, ICompanyCommandService iCompanyCommandService) {
        this.ec = ec;
        this.iCompanyQueryService = iCompanyQueryService;
        this.iCompanyCommandService = iCompanyCommandService;
    }

    public CompletionStage<Result> list() {
        return iCompanyQueryService.listCompanies().thenApplyAsync(companies -> {
            final List<CompanyListQueryDTO> postList = companies.map(data -> new CompanyListQueryDTO(data.getId(),
                    data.getNit(),
                    data.getName(),
                    data.getPhoneNumber(),
                    data.getEmail()
            )).collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }


    public CompletionStage<Result> create() {
        JsonNode json = request().body().asJson();
        final CompanyCreateCommandDTO companyCreateCommandDTO = Json.fromJson(json, CompanyCreateCommandDTO.class);
        return iCompanyCommandService.createCompany(convertToCompany(companyCreateCommandDTO)).thenApplyAsync(voidResult -> ok(), ec.current());
    }

    private Company convertToCompany(CompanyCreateCommandDTO companyCreateCommandDTO) {
        Company company = new Company();
        company.setName(companyCreateCommandDTO.getName());
        company.setNit(companyCreateCommandDTO.getNit());
        company.setEmail(companyCreateCommandDTO.getEmail());
        company.setPhoneNumber(companyCreateCommandDTO.getPhoneNumber());
        return company;
    }

}
