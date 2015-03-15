package com.tribetron.editor.io

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import com.tribetron.editor.objects.{ GameObject, TribetronMap }

object MapFileUtil {
  
    private val folder = "maps"
    
    def writeMap(map: TribetronMap) = {
      println(pretty(render(map.toJson)))
    }
  
}