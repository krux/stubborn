package com.krux.stubborn.policy

import scala.util.Random


/**
 * Implements the FullJitter algorithm in
 * https://www.awsarchitectureblog.com/2015/03/backoff.html
 */
trait ExponentialBackoffAndJitter extends Policy {

  def base: Int

  def cap: Int

  def retryDelay(attempt: Int): Int = Random.nextInt(
    Math.min(
      cap,
      base * Math.pow(2, (attempt + 1)).toInt
    )
  )

}
