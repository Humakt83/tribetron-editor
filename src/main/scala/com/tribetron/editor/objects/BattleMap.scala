package com.tribetron.editor.objects

import org.json4s._
import org.json4s.JsonDSL._
import com.tribetron.editor.gui.BattleMapSettings

class BattleMap(val battleSettings: BattleMapSettings, override val width: Int, override val height: Int) 
    extends TribetronMap(width, height) {
  
  override def toJson(story: String): JValue = {
    ("type" -> "battle") ~
       ("story" -> story) ~
       ("maxRoster" -> battleSettings.maxRoster) ~
       ("reward" -> battleSettings.reward) ~
       ("opponentTeamName" -> battleSettings.opponentTeamName) ~
       ("opponent" -> battleSettings.opponent.toString()) ~
       ("rounds" -> battleSettings.rounds) ~
       ("rows" ->
        rows.map { row =>
          row.columns.map { column =>
            ("object" -> column.objectType.jsonName)
        }})
  }
  
}