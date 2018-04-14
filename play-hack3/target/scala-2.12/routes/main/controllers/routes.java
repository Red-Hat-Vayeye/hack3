// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Mauricio/Documents/hack3/play-hack3/conf/routes
// @DATE:Fri Apr 13 21:32:32 CDT 2018

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
