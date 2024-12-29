package com.github.bkaraargirov

import adventofcode._2015.day_01.NotQuiteLisp

import com.github.bkaraargirov.adventofcode._2015.day_02.IWasToldThereWouldBeNoMath

import scala.io.Source

@main def run() =
  val filePath = "src/adventofcode/_2015/day_02/input.txt"
  val fileContents = Source.fromFile(filePath).getLines

  val noMath = IWasToldThereWouldBeNoMath()

  println(noMath.calculateNeededRibbon(fileContents))
