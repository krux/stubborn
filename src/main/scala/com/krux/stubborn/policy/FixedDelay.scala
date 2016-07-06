package com.krux.stubborn.policy


trait FixedDelay extends Policy {

  def fixedDelay: Int = FixedDelay.defaultFixedDelay

  def retryDelay(attempt: Int): Int = fixedDelay

}

object FixedDelay {

  val defaultFixedDelay: Int = 3000

  def apply(fixedDelayValue: Int = defaultFixedDelay) = new FixedDelay {
    override def fixedDelay = defaultFixedDelay
  }

}
