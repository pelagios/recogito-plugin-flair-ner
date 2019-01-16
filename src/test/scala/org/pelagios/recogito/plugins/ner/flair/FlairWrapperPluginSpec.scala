package org.pelagios.recogito.plugins.ner.flair

import java.io.File
import org.specs2.mutable._
import scala.io.Source

class FlairWrapperPluginSpec extends Specification {

  import FlairWrapperPluginSpec._

  "The wrapper plugin" should {

    "should parse the sample text" in {
      val plugin = new FlairWrapperPlugin()
      val entities = plugin.parse(SAMPLE_TEXT)
      entities.size must equalTo(4)
    }

  }

}

object FlairWrapperPluginSpec {

  lazy val SAMPLE_TEXT = {
    val path = new File(getClass.getResource("/input.txt").getPath)
    Source.fromFile(path).getLines.mkString("\n")
  }

}