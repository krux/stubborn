package com.krux.stubborn.policy

import scala.util.Random


/**
 * Implements the FullJitter algorithm in
 * https://www.awsarchitectureblog.com/2015/03/backoff.html
 */
trait ExponentialBackoffAndJitter extends Policy {

  def base: Int = ExponentialBackoffAndJitter.defaultBase

  def cap: Int = ExponentialBackoffAndJitter.defaultCap

  def retryDelay(attempt: Int): Int = Random.nextInt(
    Math.min(
      cap,
      base * Math.pow(2, (attempt + 1)).toInt
    )
  )

}

object ExponentialBackoffAndJitter {

  val defaultBase: Int = 3000

  val defaultCap: Int = 60000

  def apply(baseValue: Int = defaultBase, capValue: Int = defaultCap) = new ExponentialBackoffAndJitter {
    override def base = baseValue
    override def cap = capValue
  }

}
