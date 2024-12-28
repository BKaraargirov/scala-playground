package com.github.bkaraargirov
package adventofcode._2015.day_01

import scala.annotation.tailrec

class NotQuiteLisp:
  def findFloor(directions: String): Long =
    directions.foldRight(0)((next, currentFloor) =>
      if(next == '(') currentFloor + 1
      else if(next == ')') currentFloor - 1
      else throw Exception("Unknown directions: " + next)
    )

  /**
   *
   * @param directions
   * @return the position in direction string when we reach a given floor. -1 by default
   */
  def stopOnFloor(directions: String, targetFloor: Long = -1): Long =
    @tailrec def loop(leftoverDirections: String, currentFloor: Long): Long =
      val next = leftoverDirections.head
      val move = if(next == '(') currentFloor + 1
                else if(next == ')') currentFloor - 1
                else throw Exception("Unknown directions: " + next)

      if(move == targetFloor)
        directions.length - leftoverDirections.length + 1 // add the current move, instead of calling again tail
      else
        loop(leftoverDirections.tail, move)

    loop(directions, 0)
