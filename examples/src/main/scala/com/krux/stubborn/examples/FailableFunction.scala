package com.krux.stubborn.examples

import com.krux.stubborn.Retryable
import com.krux.stubborn.policy.ExponentialBackoffAndJitter

object FailableFunction extends Retryable with ExponentialBackoffAndJitter {

  def apply() = Failable.failBeforeSuccess(3).retry()

  def main(args: Array[String]): Unit = println(apply())

}
