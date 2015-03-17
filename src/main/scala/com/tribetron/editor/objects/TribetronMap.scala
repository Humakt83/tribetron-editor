package com.tribetron.editor.objects

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}


class TribetronMap(val width: Int, val height: Int) {
  
  var rows = initTable
  
  private def initTable : List[Row] = {
    var rows = List[Row]()
    for (i <- 0 until height) {
      rows = rows :+ new Row(width)
    }
    rows
  }
  
  def toJson: JValue = {
       ("type" -> "venture") ~
       ("rows" ->
        rows.map { row =>
          row.columns.map { column =>
            ("object" -> column.objectType.jsonName)
        }})
  }
  

}

class Row(private val numberOfColumns: Int) {
	var columns = initColumns

	private def initColumns: List[Column] = {
			var columns = List[Column]() 
			for (i <- 0 until numberOfColumns) {
				columns = columns :+ new Column(GameObjects.getGameObjects.apply(0))
			}
			columns
	}
}
  
class Column(val objectType: GameObject)