package model;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface represents all the features to be offered by a type of investment.
 * These features are supposed to be a basic set upon which other features
 * may be developed.
 */
public interface Investment extends LineChartObject {

  /**
   * Allow a user to examine the gain or loss of an investment over a specified period.
   *
   * @param startDate the start date of the period to be examined
   * @param endDate   the end date of the period to be examined
   * @return the gain or loss of the investment over a specified period
   *         -positive number indicates gain, negative number indicates loss
   */
  double fetchGainOrLoss(LocalDate startDate, LocalDate endDate);

  /**
   * Allow a user to examine the x-day moving average of an investment
   * for a specified date and a specified value of x.
   *
   * @param startDate the start date of the period to be examined
   * @param days      the positive number of days for the average to be
   *                  determined starting from startDate
   * @return the X-Day moving average
   */
  double calculateXDayMovingAvg(LocalDate startDate, int days);

  /**
   * Determine which days are x-day crossovers for an investment over a
   * specified date range and a specified value of x.
   *
   * @param startDate the start date of the specified range of days
   * @param endDate the end date of the specified range of days
   * @param days the positive number of days for the crossovers to be determined from
   * @return a list of dates that represent the X-Day crossovers for the specified parameters.
   */
  List<LocalDate> findCrossovers(LocalDate startDate, LocalDate endDate, int days);

  /**
   * gets the value of an investment at end of specified day.
   * If weekend or holiday, cycles back 1 day at a time until a closing day value can be found.
   *
   * @param targetDate the date on which value is to be fetched
   * @return the value of the investment at the end of the day
   * @throws IllegalArgumentException if no price available for any day currently or prior
   */
  double getEndOfDayVal(LocalDate targetDate) throws IllegalArgumentException;

  /**
   * Gives a meaningful name to an investment object.
   *
   * @return a meaningful string representation of an investment
   */
  String toString();
}
