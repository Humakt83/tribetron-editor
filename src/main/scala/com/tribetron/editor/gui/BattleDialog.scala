package com.tribetron.editor.gui

import com.tribetron.editor.objects.Opponent
import scalafx.scene.control.{ TextField, Label, Button, Dialog, ButtonType }

class BattleDialog extends Dialog {
  
  title = "Battlemap options"
  headerText = "Set options for battlemap"
  
    
}

case class BattleMapSettings(val maxRoster: Int, val reward: Int, val opponentTeamName: String, val opponent: Opponent.Opponent, val rounds: Int)