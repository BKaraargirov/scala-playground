package com.github.bkaraargirov
package adventofcode._2015.day_06

import scala.collection.mutable

class ProbablyAFireHazard:
  case class Lightbulb(x: Long, y: Long)

  // part 1
  def createMesh(instructions: List[String]): Long =
    val decoration = createInitialState()

    instructions.map(parseInstruction)
      .foreach((start, end, state) => {
        val changedStates = findAffectedArea(start, end)
        changedStates.foreach(lightbulb => {
          decoration(lightbulb) = state match {
            case "turn on" => true
            case "turn off" => false
            case "toggle" => !decoration(lightbulb)
          }
        })
      })

    decoration.values.count(_ == true)

  // part 2
  def createMeshWithBrightness(instructions: List[String]): Long =
    val decoration = createInitialStateWithBrigthness()

    instructions.map(parseInstruction)
      .foreach(
        (start, end, state) => {
          val changedStates = findAffectedArea(start, end)
          changedStates.foreach(
            lightbulb => {
              decoration(lightbulb) = state match {
                case "turn on" => decoration(lightbulb) + 1
                case "turn off" => if(decoration(lightbulb) > 0) decoration(lightbulb) - 1 else 0
                case "toggle" => decoration(lightbulb) + 2
              }
            })
        })

    decoration.values.sum()

  private def findAffectedArea(start: Lightbulb, end:Lightbulb): List[Lightbulb] =
    (for {
      x <- start.x to end.x
      y <- start.y to end.y
    } yield Lightbulb(x, y)
      ).toList

  private def parseInstruction(instruction: String): (Lightbulb, Lightbulb, String) =
    val pattern = """(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)""".r
    instruction match
      case pattern(action, x1, y1, x2, y2) =>
        val start = Lightbulb(x1.toInt, y1.toInt)
        val end = Lightbulb(x2.toInt, y2.toInt)
        (start, end, action)
      case _ => throw new IllegalArgumentException("Invalid instruction format")


  // part 1 init state
  private def createInitialState(): mutable.Map[Lightbulb, Boolean] =
    val points = for {
      x <- 0 to 999
      y <- 0 to 999
    } yield Lightbulb(x, y)

    val m = mutable.Map[Lightbulb, Boolean]()
    points.foreach(p => m(p) = false)

    m

  // part two init state
  private def createInitialStateWithBrigthness(): mutable.Map[Lightbulb, Long] =
    val points = for {
      x <- 0 to 999
      y <- 0 to 999
    } yield Lightbulb(x, y)

    val m = mutable.Map[Lightbulb, Long]()
    points.foreach(p => m(p) = 0)

    m