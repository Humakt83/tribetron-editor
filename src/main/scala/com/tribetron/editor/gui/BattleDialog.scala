package com.tribetron.editor.gui

import com.tribetron.editor.objects.Opponent
import scalafx.scene.control.{ TextField, Label, Button, Dialog, ButtonType}
import scalafx.scene.layout.GridPane
import scalafx.geometry.Insets

class BattleDialog extends Dialog {
  
  title = "Battlemap options"
  headerText = "Set options for battlemap"
  
  val maxRosterBox = new TextField() {
    promptText = "# of bots"
  }
  
  val rewardBox = new TextField() {
    promptText = "Reward amount"
  }
  
  val opponentTeamNameBox = new TextField() {
    promptText = "Name"
  }
  
  val opponentBox = new TextField() {
    promptText = Opponent.values.toString()
  }
  
  val roundsBox = new TextField() {
    promptText = "# of rounds"
  }
  
  val grid = new GridPane() {
    hgap = 15
    vgap = 15
    padding = Insets(20, 100, 10, 10)
    add(new Label("Roster amount:"), 0, 0)
    add(maxRosterBox, 1, 0)
    add(new Label("Reward:"), 0, 1)
    add(rewardBox, 1, 1)
    add(new Label("Opponent's team:"), 0, 2)
    add(opponentTeamNameBox, 1, 2)
    add(new Label("Opponent:"), 0, 3)
    add(opponentBox, 1, 3)
    add(new Label("Rounds:"), 0, 4)
    add(roundsBox, 1, 4)        
  }
  
  dialogPane().setContent(grid)
  
}

case class BattleMapSettings(val maxRoster: Int, val reward: Int, 
    val opponentTeamName: String, val opponent: Opponent.Opponent, val rounds: Int)