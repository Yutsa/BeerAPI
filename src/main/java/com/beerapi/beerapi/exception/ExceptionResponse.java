package com.beerapi.beerapi.exception;

import java.time.LocalDateTime;

public class ExceptionResponse
{
  private LocalDateTime timestamp;
  private String message;
  private String details;

  public ExceptionResponse(final LocalDateTime timestamp, final String message, final String details) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }
}
