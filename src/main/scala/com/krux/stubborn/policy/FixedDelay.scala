package com.krux.stubborn.policy


trait FixedDelay extends Policy {

  def fixedDelay: Int = 3000

  def retryDelay(attempt: Int): Int = fixedDelay

}
