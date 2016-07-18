package com.krux.stubborn.examples

import com.krux.stubborn.RetryEither
import com.krux.stubborn.policy.ExponentialBackoff


object RetryEitherWithoutMixin {

  def apply() = RetryEither.retry(3, ExponentialBackoff(100, 100000)) {
    Failable.leftBeforeRight(2)
  }

  def main(args: Array[String]): Unit = println(apply())

}
