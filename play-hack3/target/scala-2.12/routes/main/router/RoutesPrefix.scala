// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Mauricio/Documents/hack3/play-hack3/conf/routes
// @DATE:Fri Apr 13 21:32:32 CDT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}