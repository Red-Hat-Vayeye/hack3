// @GENERATOR:play-routes-compiler
// @SOURCE:/home/hack/hack-3/play-hack-03/conf/routes
// @DATE:Fri Apr 13 22:14:54 UTC 2018


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
