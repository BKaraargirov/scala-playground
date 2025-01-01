package com.github.bkaraargirov
package adventofcode._2015.day_05

import scala.annotation.tailrec
import scala.collection.mutable

class DoesntHeHaveInternElvesForThis:
  def countNiceV2(input: List[String]): Long =
    input.count(isNiceV2)

  def isNiceV2(input: String): Boolean =
    pairOverlap(input) && separatedPairs(input)

  def pairOverlap(input: String): Boolean =
    if(input.length < 4) return false

    /**
     * Create a map of pairs and the index of where the pair start is found in the string
     */
    val pairOccurances = input.zipWithIndex.sliding(2).foldRight(mutable.Map[String, List[Int]]())(
      (op, m) => {
        val characters = op.toArray
        val pair: String = op(0)._1.toString + op(1)._1

        if(m.contains(pair))
          m(pair) =  op(0)._2 :: m(pair)
        else
          m(pair) = List(op(0)._2)

        m
      }
    )

    pairOccurances.exists(
      (_, ocurances) => {
        if (ocurances.length < 2) false
        else if (ocurances.length > 3) true
        else
          math.abs(ocurances(0) - ocurances(1)) > 1 // There should be atlease 1 space between them ex: aaa
      })

  /**
   * Checks if there is a pair of two letters with exactly one letter between them, ex: axa, xyx
   * @param input
   */
  def separatedPairs(input: String): Boolean =
    input.sliding(3).exists(w => w(0) == w(2))


  def countNiceWords(input: List[String]): Long =
    input.count(isNice)

  def isNice(input: String): Boolean =
    if(input.length < 3) return false

    // We can easily refactor this to be one loop if performance is an issue
    containsThreeVowels(input) && twiceInARow(input) && noNaughtyPhrases(input)

  private def containsThreeVowels(input: String): Boolean =
    val vowels = List('a', 'e', 'i', 'o', 'u')
    val wantedNumber = 3

    @tailrec
    def loop(leftover: String, count: Int): Boolean =
      if(leftover.isBlank && count < wantedNumber)
        false
      else if(count == wantedNumber)
        true
      else if(vowels.contains(leftover.head))
        loop(leftover.tail, count + 1)
      else
        loop(leftover.tail, count)

    loop(input, 0)

  private def twiceInARow(input: String): Boolean =
    @tailrec
    def loop(leftover: String, prev: Char): Boolean =
      if(leftover.isBlank)
        false
      else if(leftover.head == prev)
        true
      else
        loop(leftover.tail, leftover.head)

    loop(input.tail, input.head)

  private def noNaughtyPhrases(input: String): Boolean =
    val naughtyPhrases = List("ab", "cd", "pq", "xy")

    @tailrec
    def loop(leftover: String, prev: String): Boolean =
      if(leftover.isBlank)
       true
      else if(naughtyPhrases.contains(prev + leftover.head))
       false
      else
        loop(leftover.tail, leftover.head.toString)

    loop(input.tail,input.head.toString)
