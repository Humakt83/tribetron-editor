package com.tribetron.editor.objects

import scalafx.beans.property.ObjectProperty

class GameObject(val imageUrl: String, val jsonName: String) {

}

object GameObjects {

  val imagePrefix = "/img/"
  val imagePostFix = ".png"
  val gameObjects = List(
    new GameObject(getFullImage("wall"), "wall"),
    new GameObject(getFullImage("floor"), "floor"),
    new GameObject(getFullImage("player"), "player"),
    new GameObject(getFullImage("boxbot"), "box"),
    new GameObject(getFullImage("cratebot"), "grid"),
    new GameObject(getFullImage("cannoneerbot"), "cannoneer"),
    new GameObject(getFullImage("robobasic"), "hunter"),
    new GameObject(getFullImage("medicbot"), "medic"),
    new GameObject(getFullImage("zipperbot"), "zipper"),
    new GameObject(getFullImage("radiatorbot"), "radiator"),
    new GameObject(getFullImage("destructorbot"), "destructor"),
    new GameObject(getFullImage("hackerbot"), "hacker"),
    new GameObject(getFullImage("multiplicatorbot"), "multiplicator"),
    new GameObject(getFullImage("sniperbot"), "sniper"),
    new GameObject(getFullImage("totterbot"), "totter"),
    new GameObject(getFullImage("psychobot"), "psycho"),
    new GameObject(getFullImage("trasherbot"), "trasher"),
    new GameObject(getFullImage("plasma"), "plasma"),
    new GameObject(getFullImage("mine"), "mine"),
    new GameObject(getFullImage("stunmine"), "stunmine"))

  private def getFullImage(imageName: String): String = imagePrefix + imageName + imagePostFix

  def getGameObjects: List[GameObject] = gameObjects
}