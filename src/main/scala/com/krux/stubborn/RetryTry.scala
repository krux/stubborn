package com.krux.stubborn

import scala.util.{Try, Success, Failure}

import org.slf4j.{LoggerFactory, Logger}

import com.krux.stubborn.policy.Policy

trait RetryTry { policy: Policy =>

  def logger: Logger = LoggerFactory.getLogger(getClass)

  def maxRetry: Int = RetryTry.defaultMaxRetry

  implicit class RetryTryImpl[T](action: => Try[T]) {

    def retry(): Try[T] = RetryTry.retry(maxRetry, policy, logger, 0)(action)

  }

}

object RetryTry extends RetryDefaults {

  def retry[T](
      maxRetry: Int = defaultMaxRetry,
      policy: Policy = defaultPolicy,
      logger: Logger = defaultLogger,
      currentAttempt: Int = 0
    )(action: => Try[T]): Try[T] = {

    if (currentAttempt < maxRetry)
      action match {
        case s @ Success(_) => s
        case f @ Failure(_) =>
          val delay = policy.retryDelay(currentAttempt)
          logger.info(s"Action returns Failure, retry (attempt $currentAttempt) after $delay milliseconds...")
          Thread.sleep(delay)
          retry(maxRetry, policy, logger, currentAttempt + 1)(action)
      }
    else
      action
  }

}
