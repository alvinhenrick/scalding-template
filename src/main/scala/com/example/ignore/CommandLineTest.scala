package com.example.ignore

/**
  * Created by shona on 11/18/15.
  */
case class Config(labelColumn: Int = -1, ignoreColumns: List[Int] = List(-1))

object CommandLineTest {

  def main(args: Array[String]) {

    val parser = new scopt.OptionParser[Config]("Arguments") {
      head("HPModel", "1.0")
      opt[Int]('l', "labelColumn") action { (x, c) =>
        c.copy(labelColumn = x)
      } required() valueName "<labelColumn>" text "target "

      opt[Seq[Int]]('i', "ignoreColumns") valueName "<Column1>,<Column2>,..." action { (x, c) =>
        c.copy(ignoreColumns = x.toList)
      } required() text "Columns to ignore"
    }

    // parser.parse returns Option[C]
    parser.parse(args, Config()) match {
      case Some(config) =>
      // do stuff

        println(config)

      case None =>
      // arguments are bad, error message will have been displayed
    }

  }
}

