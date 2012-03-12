/**
 * User: ryan
 * Date: 3/10/12
 */

import play.api._
import play.api.db._
import org.squeryl.{Session, SessionFactory}
import org.squeryl.adapters.PostgreSqlAdapter

object Global extends GlobalSettings {

  override def onStart(app: Application) {

    SessionFactory.concreteFactory = Some(() =>
      Session.create(
        DB.getConnection()(app),
        new PostgreSqlAdapter))

  }
}