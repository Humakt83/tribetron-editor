package com.tribetron.editor.io

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import com.tribetron.editor.objects.TribetronMap
import java.io.{ FileNotFoundException, IOException, File, FileOutputStream }

object MapFileUtil {

	private val folder = "maps/"
  private val postFix = ".json"

	private def createFolderIfItDoesNotExist = {
	  val file = new File(folder)
		if(!file.isDirectory())
			file.mkdir()
	}

	def writeMap(map: TribetronMap, name: String, story: String) = {
    createFolderIfItDoesNotExist
		val fos = new FileOutputStream(folder + name + postFix)
    fos.write(pretty(render(map.toJson(story))).getBytes)
    fos.close()
	}

}