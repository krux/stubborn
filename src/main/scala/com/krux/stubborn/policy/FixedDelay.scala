package com.krux.stubborn.policy


trait FixedDelay extends Policy {

  def fixedDelay: Int

  def retryDelay(attempt: Int): Int = fixedDelay

}
