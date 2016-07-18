package com.krux.stubborn

import org.slf4j.{Logger, LoggerFactory}

import com.krux.stubborn.policy.{Policy, ExponentialBackoffAndJitter}


trait RetryDefaults {

  def defaultLogger: Logger = LoggerFactory.getLogger(getClass)

  val defaultMaxRetry = 3

  def defaultPolicy: Policy = new ExponentialBackoffAndJitter{}

}
