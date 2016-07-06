package com.krux.stubborn.examples

import com.krux.stubborn.Retryable
import com.krux.stubborn.policy.ExponentialBackoffAndJitter

object FailableFunction extends Retryable with ExponentialBackoffAndJitter {

  def maxRetry = 5

  def base = 100

  def cap = 10000

  def shouldRetry() = {
    case e: RuntimeException => e
  }

  var i = 0

  def someFunctionThatFails(): String = {
    if (i < 3) {
      i = i + 1
      throw new RuntimeException()
    } else {
      val ret = i
      i = 0
      s"Successful at attempt $ret"
    }
  }

  def apply() = someFunctionThatFails().retry()

  def main(args: Array[String]): Unit = println(apply())
}
