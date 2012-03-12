package controllers

import play.api.data.Form
import play.api.data.Forms.{single, nonEmptyText}
import play.api.mvc.{Action, Controller}

import org.squeryl.PrimitiveTypeMode._

import com.codahale.jerkson.Json
import models.{BarDb, Bar}
import collection.mutable.ListBuffer

object Application extends Controller {

  val barForm = Form(
    single("name" -> nonEmptyText)
  )

  def index = Action {
    Ok(views.html.index(barForm))
  }

  def addBar() = Action {
    implicit request =>
      barForm.bindFromRequest.fold(
      errors => BadRequest, {
        case (name) =>
          inTransaction {
            val bar: Bar = new Bar(name)
            println(bar);
            BarDb.bars.insert(bar)
            Redirect(routes.Application.index())
          }
      }
      )
  }


  def listBars() = Action {
    inTransaction {
      val bars = from(BarDb.bars)(bar =>
        select(bar)
      )

      val json = Json.generate(bars)
      println(json)

      var customJson = "["
      bars.foreach(bar => {
        if (customJson.length > 1) customJson += ","
        customJson += "{\"name\":\"%s\"}".format(bar.name)
      })
      customJson += "]"
      println(customJson)
      Ok(customJson).as("application/json")
    }
  }

}