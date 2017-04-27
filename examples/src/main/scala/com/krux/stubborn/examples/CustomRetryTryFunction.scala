package com.krux.stubborn.examples

import com.krux.stubborn.RetryTry
import com.krux.stubborn.policy.ExponentialBackoff

object CustomRetryTryFunction extends RetryTry with ExponentialBackoff {

  override def maxRetry = 5

  override def base = 100

  override def cap = 10000

  def apply() = Failable.failureTryBeforeSuccess(3).retry()

  def main(args: Array[String]): Unit = println(apply())

}
