package com.krux.stubborn.examples


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

}
