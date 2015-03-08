package com.tribetron.editor.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene._
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{ Stops, LinearGradient }
import scalafx.scene.text.Text
import com.tribetron.editor.objects._
import scalafx.scene.image.{ ImageView, Image }
import scalafx.collections.ObservableBuffer
import scalafx.scene.layout.{ GridPane }
import scalafx.scene.Node
import com.tribetron.editor.objects.GameObject

object Editor extends JFXApp {

  stage = new PrimaryStage {
    title = "Tribetron Editor"
    scene = new Scene {
      val images = createImagePanel
      content = images
    }
  }

  private def createImagePanel: Node = {
    val imagePanel = new Group {}
    val box = new HBox()
    GameObjects.gameObjects.foreach(gameObject => box.children.add(createGameImage(gameObject.imageUrl)))
    imagePanel.children.add(box)
    imagePanel
  }

  private def createGameImage(imageUrl: String): Node = {
    new ImageView(new Image(this, imageUrl)) {
      this.fitHeight = 50.0
      this.fitWidth = 50.0
    }
  }
}