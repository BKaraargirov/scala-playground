package com.github.bkaraargirov
package adventofcode._2015.day_02

class IWasToldThereWouldBeNoMath:
  case class Present(length: Long, width: Long, height: Long)

  def calculateNeededPaper(inputFile: Iterator[String]): Long =
    val presents = parseFile(inputFile)
    presents.map(present => {
      val areas = findAreas(present)
      val smallestArea = areas.min

      areas.map(_ * 2).sum + smallestArea
    }).sum

  def calculateNeededRibbon(inputFile: Iterator[String]): Long =
    val presents = parseFile(inputFile)
    presents.map(
      present => findSmallestArea(present) + findBowSize(present)
    ).sum

  /**
   * @return the volume of the present
   */
  private def findBowSize(present: Present): Long =
    present.width * present.length * present.height

  /**
   * The smallest area is the area with the smallest 
   * @param present
   * @return
   */
  private def findSmallestArea(present: Present): Long =
    val sorted = List(present.length, present.width, present.height).sorted
    sorted(0) * 2 + sorted(1) * 2

  private def findAreas(present: Present): List[Long] =
    List(
      present.length * present.width,
      present.width * present.height,
      present.height * present.length
    )

  private def parseFile(inputFile: Iterator[String]): List[Present] =
    inputFile.map(line => {
      val dims = line.split('x').map(_.toLong)
      Present(dims(0), dims(1), dims(2))
    }).toList