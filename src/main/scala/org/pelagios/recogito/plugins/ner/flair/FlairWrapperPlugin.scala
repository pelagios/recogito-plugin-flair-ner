package org.pelagios.recogito.plugins.ner.flair

import java.io.{File, PrintWriter}
import java.util.UUID
import org.pelagios.recogito.sdk.ner._
import play.api.libs.json.{Json, JsArray, JsObject}
import scala.collection.JavaConverters._
import scala.language.postfixOps
import sys.process._

class FlairWrapperPlugin extends NERPlugin {

  override val getName = "Flair NER"

  override val getDescription = "An experimental wrapper plugin using Flair by Zalando Research"

  override val getOrganization = "Zalando Research"

  override val getVersion = "0.1"

  override val getSupportedLanguages = Seq.empty[String].asJava

  override def parse(text: String) = {
    // Write the text to a temporary file we can hand to Flair
    val tmp = File.createTempFile("flair_", ".txt")
    val writer = new PrintWriter(tmp)
    writer.write(text)
    writer.close

    // Locate the script somewhere within the /plugins folder
    val script = FlairWrapperPlugin.findPath("parse.py").get

    // Call out via commandline and collect the results
    val result = s"python ${script} ${tmp.getAbsolutePath}" !!

    // Delete the temp file
    tmp.delete()

    // The script returns JSON - parse the result...
    val json = Json.parse(result)

    // ...and convert entities to Recogito API classes
    (json \ "entities").as[Seq[JsObject]].flatMap { obj =>
      val text  = (obj \ "text").as[String]
      val start = (obj \ "start_pos").as[Int]

      // Current API only supports Place and Person - discard the rest
      val typ   = (obj \ "type").as[String] match {
        case "PER" => Some(EntityType.PERSON)
        case "LOC" => Some(EntityType.LOCATION)
        case _ => None
      }

      typ.map(t => new Entity(text, t, start))
    }.asJava
  }

}

// TODO move this into the SDK as a utility function
object FlairWrapperPlugin {

  private def findInFolder(filename: String, folder: File): Option[File] = {
    folder.listFiles.toSeq.filter(_.getName == filename).headOption match {
      case Some(file) => Some(file)

      case None => 
        val folders = folder.listFiles.filter(_.isDirectory)
        folders.flatMap(f => findInFolder(filename, f)).headOption
    }
  }

  def findPath(filename: String, rootPath: String = "plugins"): Option[String] = {
    findInFolder(filename, new File(rootPath)).map(_.getAbsolutePath)
  }

}