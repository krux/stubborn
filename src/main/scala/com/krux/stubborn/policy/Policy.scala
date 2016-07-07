package com.krux.stubborn.policy


trait Policy {

  /**
   * @param attempt the current count of the retry
   *
   * @return the number of milliseconds to wait for the next retry
   */
  def retryDelay(attempt: Int): Int

}
