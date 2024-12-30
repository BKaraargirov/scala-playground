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
    visitedHouses.size

  def useRoboSanta(directions: String): Long =
    val indexedDirections = directions.zipWithIndex
    val oddDirections: String = indexedDirections.filter((d, i) => i % 2 == 1).map(_._1).mkString
    val evenDirections = indexedDirections.filter((d, i) => i % 2 == 0).map(_._1).mkString

    val santaHouses = traverseHouses(oddDirections)
    val roboHouses = traverseHouses(evenDirections)

    (santaHouses ++ roboHouses).size // Size of the union

  /**
   *
   * @param directions
   * @return
   */
  private def traverseHouses(
    directions:String,
    currentPosition: Position = Position(0,0),
    accumulator: Set[Position] = Set(Position(0,0))
  ) : Set[Position] =
    if(directions.isBlank) return accumulator

    val nextPosition = currentPosition.move(directions.head)

    traverseHouses(directions.tail, nextPosition, accumulator + nextPosition)

