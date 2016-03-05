package com.tribetron.editor.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene._
import com.tribetron.editor.objects._
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{ HBox, VBox }
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{ Stops, LinearGradient }
import scalafx.scene.text.Text
import scalafx.scene.image.{ ImageView, Image }
import scalafx.collections.ObservableBuffer
import scalafx.scene.layout.{ GridPane }
import scalafx.scene.Node
import scalafx.scene.control.{ TextField, Label, Button, TableView, TableColumn, TableCell, CheckBox, Dialog, ButtonType }
import scalafx.beans.property.{ IntegerProperty }
import scalafx.scene.input.MouseEvent
import scalafx.Includes._
import com.tribetron.editor.objects.{ GameObject, TribetronMap, BattleMap, GameObjects, Opponent }
import com.tribetron.editor.io.MapFileUtil
import scalafx.scene.control.TextArea
import scalafx.scene.control.ButtonBar.ButtonData

object Editor extends JFXApp {
 
  val editor = this
  val mapPanel = createMapPanel
  var selectedGameImageView : ImageView = new ImageView(GameObjects.gameObjects.apply(0).picture)
  
  val storyArea = new TextArea() { 
    this.prefColumnCount = 50
    this.text.value = "A new challenge awaits."
  }
  
  val nameField = new TextField() { 
    this.prefColumnCount = 10
    this.text.value = "MapName"
  }
  
  stage = new PrimaryStage {
    title = "Tribetron Editor"
    scene = new Scene {
      val mainGroup = new Group {}
      val vBox = new VBox()
      vBox.children.addAll(createImagePanel, createInputFields, mapPanel)
      mainGroup.children.add(vBox)
      content = mainGroup
    }
  }

  private def createImagePanel: Node = {
    val box = new HBox()
    GameObjects.gameObjects.foreach(gameObject => box.children.add(createGameImage(gameObject.picture)))
    box
  }

  private def createInputFields: Node = {
    val box = new HBox()
    val vBox = new VBox()
    val xField = new TextField() { this.prefColumnCount = 2 }
    val yField = new TextField() { this.prefColumnCount = 2 }
    val battleCheckBox = new CheckBox() { this.text = "Battlemap" }
    vBox.children.add(storyArea)
    box.children.addAll(new Label("Columns:"), xField, new Label("Rows:"), yField)
    val button = new Button() {
        text = "Create Map"
        onAction = handle {
          mapPanel.children.clear()
          createMapPanelContent(mapPanel, new TribetronMap(xField.text.value.toInt, yField.text.value.toInt))
        }
    }
    box.children.add(button)
    val saveButton = new Button() {
      text = "Save"
      onAction = handle {        
        if (battleCheckBox.selected.apply()) {
          battleSettingsDialog
            .map(bms => createBattleMap(bms))
            .foreach(bm => writeMap(bm.get))
        } else {
        	convertMapPanelContentToMap.foreach(tm => writeMap(tm))          
        }
      }
    }
    box.children.addAll(new Label("Map name:"), nameField, battleCheckBox, saveButton)
    vBox.children.add(box)
    vBox
  }
  
  private def writeMap(map : TribetronMap) = {   
    MapFileUtil.writeMap(map, nameField.text.value.trim(), storyArea.text.value)
  }
  
  private def createMapPanel : VBox = {
    val mapPanel = new VBox{}
    createMapPanelContent(mapPanel, new TribetronMap(15, 10))
    mapPanel
  }
  
  private def createBattleMap(battleSettings: BattleMapSettings) : Option[BattleMap] = {
    convertMapPanelContentToMap.map(ventureMap => {
      val battleMap = new BattleMap(battleSettings, ventureMap.width, ventureMap.height)
      battleMap.rows = ventureMap.rows
      battleMap
    })
  }
  
  private def convertMapPanelContentToMap : Option[TribetronMap] = {
    val width = mapPanel.children.last.asInstanceOf[javafx.scene.layout.HBox].children.length
    val height = mapPanel.children.length
    val tribetronMap = new TribetronMap(width, height)
    def createRow(box: HBox) : Row = {
      def createColumn(image: ImageView) : Column = {
        new Column(GameObjects.getGameObjectByImage(image.image.apply()))
      }
      var row = new Row(0)
      box.children.foreach(child => row.columns = row.columns :+ createColumn(child.asInstanceOf[javafx.scene.image.ImageView]))
      row
    }
    var rows = List[Row]()
    mapPanel.children.foreach(child => rows = rows :+ createRow(child.asInstanceOf[javafx.scene.layout.HBox]))
    tribetronMap.rows = rows
    Some(tribetronMap)
  }
  
  private def createMapPanelContent(mapPanel : VBox, map: TribetronMap) = {
    	def createRow(row : Row) : Node = {
    			val rowBox = new HBox()
    			row.columns.foreach(col => rowBox.children.add(createMapImage(col.objectType.picture)))
    			rowBox
    	}
    	map.rows.foreach(row => mapPanel.children.add(createRow(row)))    
  }
  
  private def createMapImage(image: Image): Node = {
    new ImageView(image) {
      this.fitHeight = 30.0
      this.fitWidth = 30.0
      this.onMouseClicked = handle {
         this.image = selectedGameImageView.image.apply()
      }
    }
  }

  private def createGameImage(image: Image): Node = {
    new ImageView(image) {
      this.fitHeight = 30.0
      this.fitWidth = 30.0
      this.onMouseClicked = handle {
         selectedGameImageView = this
      }
    }
  }
  
  private def battleSettingsDialog : Option[BattleMapSettings] = {
    
    case class BattleSettings(val maxRoster: Int, val reward: Int, 
      val opponentTeamName: String, val opponent: String, val rounds: Int)
    
    val dialog = new Dialog[BattleSettings]() {
      initOwner(stage)
      title = "Battlemap options"
      headerText = "Set options for battlemap"
    }
  
    val okButton = new ButtonType("Ok", ButtonData.OKDone)
    dialog.dialogPane().getButtonTypes.add(okButton)
  
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
      promptText = Opponent.values.mkString(", ")
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
  
    dialog.dialogPane().setContent(grid)
    
    dialog.resultConverter = dialogButton =>
      if (dialogButton == okButton) BattleSettings(maxRosterBox.text().toInt, rewardBox.text().toInt, 
          opponentTeamNameBox.text(), opponentBox.text(), roundsBox.text().toInt)
      else null
    
    var result = dialog.showAndWait()
    
    result match {
        case Some(BattleSettings(roster, reward, team, opponent, rounds)) => 
          Some(BattleMapSettings(roster, reward, team, Opponent.withName(opponent), rounds))
        case None => None
      }
  }
  
}