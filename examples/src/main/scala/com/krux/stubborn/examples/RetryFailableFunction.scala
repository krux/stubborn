package com.krux.stubborn.examples

import com.krux.stubborn.Retryable
import com.krux.stubborn.policy.ExponentialBackoffAndJitter

object RetryFailableFunction extends Retryable with ExponentialBackoffAndJitter {

  def apply() = Failable.failBeforeSuccess(2).retry()

  def main(args: Array[String]): Unit = println(apply())

}
