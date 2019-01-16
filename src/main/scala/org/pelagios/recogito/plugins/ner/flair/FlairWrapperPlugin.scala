package org.pelagios.recogito.plugins.ner.flair

import java.io.{File, PrintWriter}
import java.util.UUID
import org.pelagios.recogito.sdk.ner._
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
    val filename = s"${UUID.randomUUID}.txt"

    // TODO make this a real tempfile!
    val tmp = new File(filename)
    val writer = new PrintWriter(tmp)
    writer.write(text)
    writer.close

    val result = s"python parse.py $filename" !

    tmp.delete()

    println(result)

    Seq.empty[Entity].asJava
  }

}