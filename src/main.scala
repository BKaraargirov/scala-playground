package com.github.bkaraargirov

import adventofcode._2015.day_01.NotQuiteLisp

import com.github.bkaraargirov.adventofcode._2015.day_02.IWasToldThereWouldBeNoMath
import com.github.bkaraargirov.adventofcode._2015.day_03.PerfectlySphericalHousesInAVacuum
import com.github.bkaraargirov.adventofcode._2015.day_04.TheIdealStockingStuffer
import com.github.bkaraargirov.adventofcode._2015.day_05.DoesntHeHaveInternElvesForThis
import com.github.bkaraargirov.adventofcode._2015.day_06.ProbablyAFireHazard

import scala.io.Source

@main def run() =
  val filePath = "src/adventofcode/_2015/day_06/input.txt"
  val fileContents = Source.fromFile(filePath).getLines

  val decoration = ProbablyAFireHazard()


  val answer = decoration.createMeshWithBrightness(fileContents.toList)

  println(answer)
