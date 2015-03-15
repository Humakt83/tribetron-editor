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
import scalafx.scene.input.MouseEvent
import scalafx.Includes._
import com.tribetron.editor.objects.{ GameObject, TribetronMap }
import com.tribetron.editor.io.MapFileUtil

object Editor extends JFXApp {
 
  val editor = this
  var map = new TribetronMap(15, 15)
  val mapPanel = createMapPanel
  var selectedGameImageView : ImageView = new ImageView(GameObjects.gameObjects.apply(0).picture)

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
    val xField = new TextField() { this.prefColumnCount = 2 }
    val yField = new TextField() { this.prefColumnCount = 2 }
    box.children.addAll(new Label("Columns:"), xField, new Label("Rows:"), yField)
    val button = new Button() {
        text = "Create Map"
        onAction = handle {
          map = new TribetronMap(xField.text.value.toInt, yField.text.value.toInt)
          mapPanel.children.clear()
          createMapPanelContent(mapPanel)
        }
    }
    box.children.add(button)
    val saveButton = new Button() {
      text = "Save"
      onAction = handle {
        MapFileUtil.writeMap(convertMapPanelContentToMap.get, "testi")
      }
    }
    box.children.add(saveButton)
    box
  }
  
  private def createMapPanel : VBox = {
    val mapPanel = new VBox{}
    createMapPanelContent(mapPanel)
    mapPanel
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
    mapPanel.children.foreach(child => tribetronMap.rows = tribetronMap.rows :+ createRow(child.asInstanceOf[javafx.scene.layout.HBox]))
    Some(tribetronMap)
  }
  
  private def createMapPanelContent(mapPanel : VBox) = {
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
}