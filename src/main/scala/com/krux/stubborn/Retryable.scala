package com.krux.stubborn

import org.slf4j.LoggerFactory
import org.slf4j.Logger

import com.krux.stubborn.policy.Policy


trait Retryable { this: Policy =>

  def logger: Logger = LoggerFactory.getLogger(getClass)

  def maxRetry: Int = 3

  def shouldRetry(): PartialFunction[Throwable, Throwable] = {
    case e: RuntimeException => e
  }

  implicit class RetryableImpl[A](action: => A) {

    def retry(n: Int = 0): A = {
      if (n < maxRetry) {
        try {
          action
        } catch {
          shouldRetry.andThen { e =>
            val delay = retryDelay(n)
            logger.info(s"caught exception: ${e.getMessage}\n Retry (Attempt $n) after $delay milliseconds...")
            Thread.sleep(delay)
            retry(n + 1)
          }
        }
      } else {
        action
      }
    }

  }

}
