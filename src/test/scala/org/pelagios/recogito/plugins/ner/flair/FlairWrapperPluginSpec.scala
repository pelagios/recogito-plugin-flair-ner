package org.pelagios.recogito.plugins.ner.flair

import java.io.File
import org.specs2.mutable._
import scala.io.Source

class FlairWrapperPluginSpec extends Specification {

  import FlairWrapperPlugin._

  "The wrapper plugin" should {

    "should parse the sample text" in {
      val plugin = new FlairWrapperPlugin()

      1 must equalTo(1)
    }

  }

}

object FlairWrapperPlugin {

  lazy val SAMPLE_TEXT = {
    val path = new File(getClass.getResource("/input.txt").getPath)
    Source.fromFile(path).getLines.mkString("\n")
  }

}