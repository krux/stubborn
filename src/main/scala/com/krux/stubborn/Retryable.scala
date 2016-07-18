package com.krux.stubborn

import org.slf4j.{LoggerFactory, Logger}

import com.krux.stubborn.policy.Policy


trait Retryable { policy: Policy =>

  def logger: Logger = LoggerFactory.getLogger(getClass)

  def maxRetry: Int = Retryable.defaultMaxRetry

  def shouldRetry(): PartialFunction[Throwable, Throwable] = Retryable.defaultShouldRetry()

  implicit class RetryableImpl[A](action: => A) {

    def retry(): A = Retryable.retry(maxRetry, policy, shouldRetry, logger, 0)(action)

  }

}

object Retryable extends RetryDefaults {

  def defaultShouldRetry(): PartialFunction[Throwable, Throwable] = {
    case e: RuntimeException => e
  }

  def retry[A](
      maxRetry: Int = defaultMaxRetry,
      policy: Policy,
      shouldRetry: PartialFunction[Throwable, Throwable] = defaultShouldRetry,
      logger: Logger = defaultLogger,
      currentAttempt: Int = 0
    )(action: => A): A = {

    if (currentAttempt < maxRetry) {
      try {
        action
      } catch {
        shouldRetry.andThen { e =>
          val delay = policy.retryDelay(currentAttempt)
          logger.info(s"Caught exception: ${e.getMessage}\n Retry (Attempt ${currentAttempt}) after $delay milliseconds...")
          Thread.sleep(delay)
          retry(maxRetry, policy, shouldRetry, logger, currentAttempt + 1)(action)
        }
      }
    } else {
      action
    }
  }

}
