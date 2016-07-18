package com.krux.stubborn

import org.slf4j.{LoggerFactory, Logger}

import com.krux.stubborn.policy.Policy


trait RetryEither { policy: Policy =>

  def logger: Logger = LoggerFactory.getLogger(getClass)

  def maxRetry: Int = Retryable.defaultMaxRetry

  implicit class RetryableImpl[L, R](action: => Either[L, R]) {

    def retry(): Either[L, R] = RetryEither.retry(maxRetry, policy, logger, 0)(action)

  }

}

object RetryEither extends RetryDefaults {

  def retry[L, R](
      maxRetry: Int = defaultMaxRetry,
      policy: Policy = defaultPolicy,
      logger: Logger = defaultLogger,
      currentAttempt: Int = 0
    )(action: => Either[L, R]): Either[L, R] = {

    if (currentAttempt < maxRetry)
      action match {
        case r @ Right(_) =>
          r
        case l @ Left(_) =>
          val delay = policy.retryDelay(currentAttempt)
          logger.info(s"Action returns left, retry (attempt $currentAttempt) after $delay milliseconds...")
          Thread.sleep(delay)
          retry(maxRetry, policy, logger, currentAttempt + 1)(action)
      }
    else
      action
  }

}
