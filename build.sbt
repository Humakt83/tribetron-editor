import AssemblyKeys._

assemblySettings

scalaVersion := "2.11.5"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.31-R7" withSources() withJavadoc()

jarName in assembly := "TribetronEditor.jar"

name := "TribetronEditor"

version := "0.1"

mainClass in Compile := Some("com.tribetron.editor.gui.Editor")