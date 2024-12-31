package com.github.bkaraargirov

import adventofcode._2015.day_01.NotQuiteLisp

import com.github.bkaraargirov.adventofcode._2015.day_02.IWasToldThereWouldBeNoMath
import com.github.bkaraargirov.adventofcode._2015.day_03.PerfectlySphericalHousesInAVacuum
import com.github.bkaraargirov.adventofcode._2015.day_04.TheIdealStockingStuffer

import scala.io.Source

@main def run() =
  val filePath = "src/adventofcode/_2015/day_03/input.txt"
  val fileContents = Source.fromFile(filePath).getLines.mkString

  val theIdealStockingStuffer = TheIdealStockingStuffer()
  val answer = theIdealStockingStuffer.findLeadingZeroes("000000")

  println(answer)
