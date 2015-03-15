package com.tribetron.editor.objects

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}


class TribetronMap {
  var width: Int = 5
  var height: Int = 5
  
  var rows = List[Row]()
  
  def resetTable = {
    rows = List[Row]()
    for (i <- 0 until height) {
      rows = rows :+ new Row(width)
    }
  }
  
  def toJson: JValue = {
    ("map" ->
      ("rows" ->
        rows.map { row =>
          row.columns.map { column =>
            ("object" -> column.objectType.jsonName)
        }}))
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
  