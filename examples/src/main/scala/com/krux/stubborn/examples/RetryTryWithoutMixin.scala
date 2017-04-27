package com.krux.stubborn.examples

import com.krux.stubborn.RetryTry
import com.krux.stubborn.policy.ExponentialBackoff


object RetryTryWithoutMixin {

  def apply() = RetryTry.retry(3, ExponentialBackoff(100, 100000)) {
    Failable.failureTryBeforeSuccess(2)
  }

  def main(args: Array[String]): Unit = println(apply())

}
