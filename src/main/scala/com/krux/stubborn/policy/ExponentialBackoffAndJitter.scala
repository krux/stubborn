package com.krux.stubborn.policy

import scala.util.Random


/**
 * Implements the FullJitter algorithm in
 * https://www.awsarchitectureblog.com/2015/03/backoff.html
 */
trait ExponentialBackoffAndJitter extends ExponentialBackoff with Policy {

  override def base: Int = ExponentialBackoffAndJitter.defaultBase

  override def cap: Int = ExponentialBackoffAndJitter.defaultCap

  override def retryDelay(attempt: Int): Int = Random.nextInt(super.retryDelay(attempt))

}

object ExponentialBackoffAndJitter {

  val defaultBase: Int = 3000

  val defaultCap: Int = 60000

  def apply(baseValue: Int = defaultBase, capValue: Int = defaultCap) = new ExponentialBackoffAndJitter {
    override def base = baseValue
    override def cap = capValue
  }

}
