package org.pelagios.recogito.plugins.ner.flair

import org.pelagios.recogito.sdk.ner._
import scala.collection.JavaConverters._

class FlairWrapperPlugin extends NERPlugin {

  override val getName = "Flair NER"

  override val getDescription = "An experimental wrapper plugin using Flair by Zalando Research"

  override val getOrganization = "Zalando Research"

  override val getVersion = "0.1"

  override val getSupportedLanguages = Seq.empty[String].asJava

  override def parse(text: String) = {

    // TODO

    Seq.empty[Entity].asJava
  }


}