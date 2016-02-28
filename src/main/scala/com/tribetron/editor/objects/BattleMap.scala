package com.tribetron.editor.objects

import org.json4s._
import org.json4s.JsonDSL._

class BattleMap(val maxRoster: Int, val reward: Int, val opponentTeamName: String, val opponent: Opponent.Opponent, val rounds: Int, 
    override val width: Int, override val height: Int) extends TribetronMap(width, height) {
  
  override def toJson(story: String): JValue = {
    ("type" -> "battle") ~
       ("story" -> story) ~
       ("maxRoster" -> maxRoster) ~
       ("reward" -> reward) ~
       ("maxRoster" -> maxRoster) ~
       ("opponentTeamName" -> opponentTeamName) ~
       ("opponent" -> opponent.toString()) ~
       ("rows" ->
        rows.map { row =>
          row.columns.map { column =>
            ("object" -> column.objectType.jsonName)
        }})
  }
  
}