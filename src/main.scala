package com.github.bkaraargirov

import adventofcode._2015.day_01.NotQuiteLisp

import scala.io.Source

@main def run() =
  val filePath = "src/adventofcode/_2015/day_01/input.txt"
  val fileContents = Source.fromFile(filePath).getLines.mkString("\n")

  val notQuiteLisp = NotQuiteLisp()
  val floor = notQuiteLisp.stopOnFloor(fileContents)

  println(floor)
