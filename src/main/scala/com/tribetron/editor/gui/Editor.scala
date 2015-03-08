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
import scalafx.scene.control.{ TextField, Label }
import com.tribetron.editor.objects.GameObject

object Editor extends JFXApp {

  stage = new PrimaryStage {
    title = "Tribetron Editor"
    scene = new Scene {
      val mainGroup = new Group {}
      val vBox = new VBox()
      vBox.children.add(createImagePanel)
      vBox.children.add(createInputFields)
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
    val xField = new TextField() { this.maxWidth = 40.0 }
    val yField = new TextField() { this.maxWidth = 40.0 }
    box.children.add(new Label("Columns:"))
    box.children.add(xField)
    box.children.add(new Label("Rows:"))
    box.children.add(yField)
    box
  }

  private def createGameImage(imageUrl: String): Node = {
    new ImageView(new Image(this, imageUrl)) {
      this.fitHeight = 30.0
      this.fitWidth = 30.0
    }
  }
}