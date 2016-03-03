package com.tribetron.editor.gui

import com.tribetron.editor.objects.Opponent

case class BattleMapSettings(val maxRoster: Int, val reward: Int, 
    val opponentTeamName: String, val opponent: Opponent.Opponent, val rounds: Int)