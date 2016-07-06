package com.krux.stubborn.examples

import com.krux.stubborn.Retryable
import com.krux.stubborn.policy.ExponentialBackoffAndJitter

object CustomRetryFailableFunction extends Retryable with ExponentialBackoffAndJitter {

  override def maxRetry = 5

  override def base = 100

  override def cap = 10000

  override def shouldRetry() = {
    case e: RuntimeException => e
  }

  def apply() = Failable.failBeforeSuccess(3).retry()

  def main(args: Array[String]): Unit = println(apply())

}
