package com.tribetron.editor.objects

import scalafx.scene.image.Image

import scalafx.beans.property.ObjectProperty

class Bot(imageUrl: String) {

  val imageRes = new ObjectProperty(this, "bot-image", new Image(imageUrl))

}