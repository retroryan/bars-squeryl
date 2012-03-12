package models


import org.squeryl._

import org.squeryl.Schema
import org.codehaus.jackson.annotate.JsonIgnoreProperties

class BarDbObject extends KeyedEntity[Long] {
  val id: Long = 0
}

@JsonIgnoreProperties(Array("persisted"))
class Bar(val name: String) extends BarDbObject {
}


object BarDb extends Schema {
  val bars = table[Bar]("BAR")

}