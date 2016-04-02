package com.tribetron.editor.objects

import scalafx.beans.property.ObjectProperty
import javafx.scene.image.Image

class GameObject(val picture: Image, val jsonName: String) {

}

object GameObjects {

  val imagePrefix = "/img/"
  val imagePostFix = ".png"
  val gameObjects = List(
    new GameObject(getFullImage("wall"), "wall"),
    new GameObject(getFullImage("floor"), "floor"),
    new GameObject(getFullImage("player"), "player"),
    new GameObject(getFullImage("treasure"), "treasure"),
    new GameObject(getFullImage("money"), "money"),
    new GameObject(getFullImage("toolkit"), "toolkit"),
    new GameObject(getFullImage("boxbot"), "box"),
    new GameObject(getFullImage("cratebot"), "crate"),
    new GameObject(getFullImage("cannoneerbot"), "cannoneer"),
    new GameObject(getFullImage("bombbot"), "bomb"),
    new GameObject(getFullImage("nukabot"), "nuka"),
    new GameObject(getFullImage("cannonbot"), "cannon"),
    new GameObject(getFullImage("robobasic"), "hunter"),
    new GameObject(getFullImage("medicbot"), "medic"),
    new GameObject(getFullImage("doctorbot"), "doctor"),
    new GameObject(getFullImage("emanatorbot"), "emanator"),
    new GameObject(getFullImage("lazorbot"), "lazor"),
    new GameObject(getFullImage("zipperbot"), "zipper"),
    new GameObject(getFullImage("radiatorbot"), "radiator"),
    new GameObject(getFullImage("destructorbot"), "destructor"),
    new GameObject(getFullImage("hackerbot"), "hacker"),
    new GameObject(getFullImage("trapperbot"), "trapper"),
    new GameObject(getFullImage("multiplicatorbot"), "multiplicator"),
    new GameObject(getFullImage("sniperbot"), "sniper"),
    new GameObject(getFullImage("totterbot"), "totter"),
    new GameObject(getFullImage("psychobot"), "psycho"),
    new GameObject(getFullImage("trasherbot"), "trasher"),
    new GameObject(getFullImage("hottotbot"), "hottot"),
    new GameObject(getFullImage("colossusbot"), "colossus"),
    new GameObject(getFullImage("megahunterbot"), "megaHunter"),
    new GameObject(getFullImage("tauronbot"), "tauron"),
    new GameObject(getFullImage("disablorbot"), "disablor"),
    new GameObject(getFullImage("combinatorBbot"), "atomitum"),
    new GameObject(getFullImage("combinatorAbot"), "plutan"),
    new GameObject(getFullImage("strongboxbot"), "strongbox"),
    new GameObject(getFullImage("spikebot"), "spike"),
    new GameObject(getFullImage("quickobot"), "quicko"),
    new GameObject(getFullImage("kamikazebot"), "kamikaze"),
    new GameObject(getFullImage("hackrambot"), "hackram"),
    new GameObject(getFullImage("plasma"), "plasma"),
    new GameObject(getFullImage("mine"), "mine"),
    new GameObject(getFullImage("stunmine"), "stunmine"))

  private def getFullImage(imageName: String): Image = new Image(imagePrefix + imageName + imagePostFix)

  def getGameObjects: List[GameObject] = gameObjects
  
  def getGameObjectByImage(picture: Image): GameObject = gameObjects.filter { go => go.picture.equals(picture) }.last 
}