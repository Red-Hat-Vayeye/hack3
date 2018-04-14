
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Hack Puebla 2018")/*3.26*/ {_display_(Seq[Any](format.raw/*3.28*/("""
  """),format.raw/*4.3*/("""<h1>Hack Puebla 2018</h1>
  <h3>Rutas Disponibles del API</h3>
  <ul>
  	<li><a href="/twitter">/twitter</a></li>
  	<li><a href="/twitter/keywords">/twitter/keywords</a></li>
  	<li><a href="/twitter/geolocation">/twitter/geolocation</a></li>
  	<li><a href="/keywords">/keywords</a></li>
  	<li><a href="/geolocation">/geolocation</a></li>
  </ul>
""")))}),format.raw/*13.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Apr 14 00:51:49 CDT 2018
                  SOURCE: C:/Users/Mauricio/Documents/hack3/play-hack3/app/views/index.scala.html
                  HASH: 749e33888a364bd146ff3bf1e99d0883fad5309b
                  MATRIX: 941->1|1037->3|1067->8|1099->32|1138->34|1168->38|1558->398
                  LINES: 28->1|33->1|35->3|35->3|35->3|36->4|45->13
                  -- GENERATED --
              */
          