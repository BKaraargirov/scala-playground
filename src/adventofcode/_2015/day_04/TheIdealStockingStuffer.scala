package com.github.bkaraargirov
package adventofcode._2015.day_04

import java.security.MessageDigest
import scala.annotation.tailrec
import scala.collection.parallel.CollectionConverters.ImmutableSeqIsParallelizable

class TheIdealStockingStuffer:
  private val md = MessageDigest.getInstance("MD5")

  def findLeadingZeroes(startPhrase: String, secretKey: String = "yzbqklnj"): Long =
    @tailrec
    def loop(counter: Long): Long =
      val phrase = secretKey + counter
      val hash = md5Hash(phrase)

      if(hash.startsWith(startPhrase))
        counter
      else
        loop(counter + 1)

    loop(0)

  def md5Hash(text: String): String =
    md.synchronized {
      md.reset()
      val digest = md.digest(text.getBytes)
      digest.map("%02x".format(_)).mkString
    }