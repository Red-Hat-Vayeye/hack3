// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Mauricio/Documents/hack3/play-hack3/conf/routes
// @DATE:Sat Apr 14 00:15:53 CDT 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: controllers.HomeController,
  // @LINE:7
  TwitterStreamController_2: controllers.TwitterStreamController,
  // @LINE:8
  TwitterKeyWordController_4: controllers.TwitterKeyWordController,
  // @LINE:9
  WordClassifierController_0: controllers.WordClassifierController,
  // @LINE:10
  GeolocationController_3: controllers.GeolocationController,
  // @LINE:13
  Assets_5: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: controllers.HomeController,
    // @LINE:7
    TwitterStreamController_2: controllers.TwitterStreamController,
    // @LINE:8
    TwitterKeyWordController_4: controllers.TwitterKeyWordController,
    // @LINE:9
    WordClassifierController_0: controllers.WordClassifierController,
    // @LINE:10
    GeolocationController_3: controllers.GeolocationController,
    // @LINE:13
    Assets_5: controllers.Assets
  ) = this(errorHandler, HomeController_1, TwitterStreamController_2, TwitterKeyWordController_4, WordClassifierController_0, GeolocationController_3, Assets_5, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, TwitterStreamController_2, TwitterKeyWordController_4, WordClassifierController_0, GeolocationController_3, Assets_5, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """twitter""", """controllers.TwitterStreamController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """twitter/keyword""", """controllers.TwitterKeyWordController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """words""", """controllers.WordClassifierController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """geolocation""", """controllers.GeolocationController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_TwitterStreamController_index1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("twitter")))
  )
  private[this] lazy val controllers_TwitterStreamController_index1_invoker = createInvoker(
    TwitterStreamController_2.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TwitterStreamController",
      "index",
      Nil,
      "GET",
      this.prefix + """twitter""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_TwitterKeyWordController_index2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("twitter/keyword")))
  )
  private[this] lazy val controllers_TwitterKeyWordController_index2_invoker = createInvoker(
    TwitterKeyWordController_4.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TwitterKeyWordController",
      "index",
      Nil,
      "GET",
      this.prefix + """twitter/keyword""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_WordClassifierController_index3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("words")))
  )
  private[this] lazy val controllers_WordClassifierController_index3_invoker = createInvoker(
    WordClassifierController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WordClassifierController",
      "index",
      Nil,
      "GET",
      this.prefix + """words""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_GeolocationController_index4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("geolocation")))
  )
  private[this] lazy val controllers_GeolocationController_index4_invoker = createInvoker(
    GeolocationController_3.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeolocationController",
      "index",
      Nil,
      "GET",
      this.prefix + """geolocation""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_5.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:7
    case controllers_TwitterStreamController_index1_route(params@_) =>
      call { 
        controllers_TwitterStreamController_index1_invoker.call(TwitterStreamController_2.index)
      }
  
    // @LINE:8
    case controllers_TwitterKeyWordController_index2_route(params@_) =>
      call { 
        controllers_TwitterKeyWordController_index2_invoker.call(TwitterKeyWordController_4.index)
      }
  
    // @LINE:9
    case controllers_WordClassifierController_index3_route(params@_) =>
      call { 
        controllers_WordClassifierController_index3_invoker.call(WordClassifierController_0.index)
      }
  
    // @LINE:10
    case controllers_GeolocationController_index4_route(params@_) =>
      call { 
        controllers_GeolocationController_index4_invoker.call(GeolocationController_3.index)
      }
  
    // @LINE:13
    case controllers_Assets_versioned5_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_5.versioned(path, file))
      }
  }
}
