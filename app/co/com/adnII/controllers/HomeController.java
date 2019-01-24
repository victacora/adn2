package co.com.adnII.controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller {


    public Result index() {
        return ok("Api-Companies");
    }

}
