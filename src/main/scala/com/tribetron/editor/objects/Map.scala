package com.tribetron.editor.objects

import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}

class Map {
  var width = 5
  var height = 5
  
  var rows = List[Row]()
  
  def resetTable = {
    rows = List[Row]()
    for (i <- 0 until height) {
      rows = rows :+ new Row(width)
    }
  }
  

  

}

class Row(private val numberOfColumns: Int) {
	val columns = setColumns

	private def setColumns: List[Column] = {
			var columns = List[Column]() 
			for (i <- 0 until numberOfColumns) {
				columns = columns :+ new Column()
			}
			columns
	}
}
  
class Column {
	val objectType = GameObjects.getGameObjects.last
}
  