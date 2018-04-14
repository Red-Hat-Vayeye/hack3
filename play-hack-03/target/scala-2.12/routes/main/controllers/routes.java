// @GENERATOR:play-routes-compiler
// @SOURCE:/home/hack/hack-3/play-hack-03/conf/routes
// @DATE:Fri Apr 13 22:14:54 UTC 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseTwitterStreamController TwitterStreamController = new controllers.ReverseTwitterStreamController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseTwitterStreamController TwitterStreamController = new controllers.javascript.ReverseTwitterStreamController(RoutesPrefix.byNamePrefix());
  }

}
