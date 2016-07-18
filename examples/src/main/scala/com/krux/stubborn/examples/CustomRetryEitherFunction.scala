package com.krux.stubborn.examples

import com.krux.stubborn.RetryEither
import com.krux.stubborn.policy.ExponentialBackoff

object CustomRetryEitherFunction extends RetryEither with ExponentialBackoff {

  override def maxRetry = 5

  override def base = 100

  override def cap = 10000

  def apply() = Failable.leftBeforeRight(3).retry()

  def main(args: Array[String]): Unit = println(apply())

}
