package com.github.bkaraargirov
package adventofcode._2015.day_03

import scala.collection.mutable

class PerfectlySphericalHousesInAVacuum:
  case class Position(x: Long, y: Long):
    def move(direction: Char): Position =
      direction match
        case '<' => moveLeft()
        case '>' => moveRight()
        case '^' => moveUp()
        case 'v' => moveDown()

    private def moveLeft(): Position = Position(x - 1, y)
    private def moveRight(): Position = Position(x + 1, y)
    private def moveUp(): Position = Position(x, y + 1)
    private def moveDown(): Position = Position(x, y - 1)

  def countHousesWithPresents(directions: String): Long =
    val visitedHouses = traverseHouses(directions)
    visitedHouses.keys.size

  def useRoboSanta(directions: String): Long =
    val indexedDirections = directions.zipWithIndex
    val oddDirections: String = indexedDirections.filter((d, i) => i % 2 == 1).map(_._1).mkString
    val evenDirections = indexedDirections.filter((d, i) => i % 2 == 0).map(_._1).mkString
    
    val santaHouses = traverseHouses(oddDirections).keys.toSet
    val roboHouses = traverseHouses(evenDirections).keys.toSet

    (santaHouses ++ roboHouses).size // Size of the union

  /**
   *
   * @param directions
   * @return
   */
  private def traverseHouses(
    directions:String,
    currentPosition: Position = Position(0,0),
    accumulator: mutable.Map[Position, Long] = mutable.Map(Position(0,0) -> 1)
  ) : Map[Position, Long] =
    if(directions.isBlank) return accumulator.toMap

    val nextPosition = currentPosition.move(directions.head)
    val currentCount = accumulator.getOrElse(nextPosition, 0L)
    accumulator.put(nextPosition, currentCount + 1)

    traverseHouses(directions.tail, nextPosition, accumulator)

