package com.github.bkaraargirov

import adventofcode._2015.day_01.NotQuiteLisp

import com.github.bkaraargirov.adventofcode._2015.day_02.IWasToldThereWouldBeNoMath
import com.github.bkaraargirov.adventofcode._2015.day_03.PerfectlySphericalHousesInAVacuum

import scala.io.Source

@main def run() =
  val filePath = "src/adventofcode/_2015/day_03/input.txt"
  val fileContents = Source.fromFile(filePath).getLines.mkString

  val housesInAVacuum = PerfectlySphericalHousesInAVacuum()

  println(housesInAVacuum.countHousesWithPresents(fileContents))
  println(housesInAVacuum.useRoboSanta(fileContents))
