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

object Editor extends JFXApp {

  val imageUrls = List("/img/boxbot.png", "/img/cratebot.png")

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
    imageUrls.foreach(imageUrl => box.children.add(createGameImage(imageUrl)))
    imagePanel.children.add(box)
    imagePanel
  }

  private def createGameImage(imageUrl: String): Node = {
    new ImageView(new Image(this, imageUrl)) {
      this.fitHeight = 25.0
      this.fitWidth = 25.0
    }
  }
}