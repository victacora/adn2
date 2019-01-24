package co.com.adnII.model.context;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class CompanyExecutionContext extends CustomExecutionContext {

    @Inject
    public CompanyExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "company.repository");
    }
}

