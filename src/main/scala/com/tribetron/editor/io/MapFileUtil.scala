package com.tribetron.editor.io

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import com.tribetron.editor.objects.TribetronMap
import java.io.{ ObjectOutputStream, FileNotFoundException, IOException, File, FilenameFilter, FileOutputStream }

object MapFileUtil {

	private val folder = "maps/"
  private val postFix = ".json"

	private def createFolderIfItDoesNotExist = {
	  val file = new File(folder)
		if(!file.isDirectory())
			file.mkdir()
	}

	def writeMap(map: TribetronMap, name: String) = {
    createFolderIfItDoesNotExist
		val oos = new ObjectOutputStream(new FileOutputStream(folder + name + postFix))
    oos.writeObject(pretty(render(map.toJson)))
    oos.close()
	}

}