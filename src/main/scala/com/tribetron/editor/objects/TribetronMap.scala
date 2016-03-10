package com.tribetron.editor.objects

import org.json4s._
import org.json4s.JsonDSL._

class TribetronMap(val width: Int, val height: Int) {
  
  var rows = initTable
  
  private def initTable : List[Row] = {
    var rows = List[Row]()
    for (i <- 0 until height) {
      rows = rows :+ new Row(width)
    }
    rows
  }
  
  def toJson(story: String): JValue = {
       ("type" -> "venture") ~
       ("story" -> story) ~
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
				columns = columns :+ new Column(GameObjects.getGameObjects.apply(1))
			}
			columns
	}
}
  
class Column(val objectType: GameObject)