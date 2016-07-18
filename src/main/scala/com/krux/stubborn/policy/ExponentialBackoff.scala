package com.krux.stubborn.policy


trait ExponentialBackoff extends Policy {

  def base: Int = ExponentialBackoffAndJitter.defaultBase

  def cap: Int = ExponentialBackoffAndJitter.defaultCap

  def retryDelay(attempt: Int): Int = Math.min(
    cap,
    base * Math.pow(2, (attempt + 1)).toInt
  )

}

object ExponentialBackoff {

  val defaultBase: Int = 3000

  val defaultCap: Int = 60000

  def apply(baseValue: Int = defaultBase, capValue: Int = defaultCap) = new ExponentialBackoff {
    override def base = baseValue
    override def cap = capValue
  }

}
