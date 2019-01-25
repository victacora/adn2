package co.com.adnII.infrastructure.persistence.jpa.context;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class CompanyExecutionContext extends CustomExecutionContext {

    @Inject
    public CompanyExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "company.repository");
    }
}

