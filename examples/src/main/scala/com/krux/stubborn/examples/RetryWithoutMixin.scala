package com.krux.stubborn.examples

import com.krux.stubborn.Retryable
import com.krux.stubborn.policy.ExponentialBackoffAndJitter


object RetryWithoutMixin {

  def apply() = Retryable.retry(3, ExponentialBackoffAndJitter(100, 100000)) {
    Failable.failBeforeSuccess(2)
  }

  def main(args: Array[String]): Unit = println(apply())

}
