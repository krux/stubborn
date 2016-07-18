package com.krux.stubborn.examples


/**
 * Functions that fails a few times before it can succeed
 */
object Failable {

  var currentAttempt = 0

  def failBeforeSuccess(failAttempts: Int): String = {
    if (currentAttempt < failAttempts) {
      currentAttempt += 1
      throw new RuntimeException("failed")
    } else {
      "success"
    }
  }

  def leftBeforeRight(failAttempts: Int): Either[String, String] = {
    if (currentAttempt < failAttempts) {
      currentAttempt += 1
      Left("failed")
    } else {
      Right("success")
    }
  }

}
