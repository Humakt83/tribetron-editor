package com.tribetron.editor.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene._
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{ HBox, VBox }
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{ Stops, LinearGradient }
import scalafx.scene.text.Text
import com.tribetron.editor.objects._
import scalafx.scene.image.{ ImageView, Image }
import scalafx.collections.ObservableBuffer
import scalafx.scene.layout.{ GridPane }
import scalafx.scene.Node
import scalafx.scene.control.{ TextField, Label, Button, TableView, TableColumn, TableCell }
import scalafx.beans.property.{ IntegerProperty }
import scalafx.Includes._
import com.tribetron.editor.objects.{ GameObject, Map }

object Editor extends JFXApp {
 
  val editor = this
  val map = new Map
  map.resetTable
  val mapPanel = createMapPanel

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
    GameObjects.gameObjects.foreach(gameObject => box.children.add(createGameImage(gameObject.imageUrl)))
    box
  }

  private def createInputFields: Node = {
    val box = new HBox()
    val xField = new TextField() { this.prefColumnCount = 2 }
    val yField = new TextField() { this.prefColumnCount = 2 }
    box.children.addAll(new Label("Columns:"), xField, new Label("Rows:"), yField)
    val button = new Button() {
        text = "Create Map"
        onAction = handle {
          map.width = xField.text.value.toInt
          map.height = yField.text.value.toInt
          map.resetTable
          mapPanel.children.clear()
          createMapPanelContent(mapPanel)
        }
    }
    box.children.add(button)
    box
  }
  
  private def createMapPanel : VBox = {
    val mapPanel = new VBox{}
    createMapPanelContent(mapPanel)
    mapPanel
  }
  
  private def createMapPanelContent(mapPanel : VBox) = {
    	def createRow(row : Row) : Node = {
    			val rowBox = new HBox()
    			row.columns.foreach(col => rowBox.children.add(createGameImage(col.objectType.imageUrl)))
    			rowBox
    	}
    	map.rows.foreach(row => mapPanel.children.add(createRow(row)))    
  }

  private def createGameImage(imageUrl: String): Node = {
    new ImageView(new Image(this, imageUrl)) {
      this.fitHeight = 30.0
      this.fitWidth = 30.0
    }
  }
}