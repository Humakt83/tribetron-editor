import AssemblyKeys._

assemblySettings

scalaVersion := "2.11.5"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.60-R9" withSources() withJavadoc()

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.11" withSources() withJavadoc()

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.11" withSources() withJavadoc()

jarName in assembly := "TribetronEditor.jar"

name := "TribetronEditor"

version := "0.1"

mainClass in Compile := Some("com.tribetron.editor.gui.Editor")