/**
 * this is the class for MyDate object.
 */
public class MyDate {
  int day;
  int month;
  int year;

  /**
   * MyDate constructor takes a day, a month, and a year.
   *
   * @param day   is the given day.
   * @param month is the given month.
   * @param year  is the given year.
   * @throws IllegalArgumentException if the given day pass the maximum number of days
   *                                  in that month given, or if the month is not
   *                                  between 1 and 12, or if year is negative
   *                                  or over 9999.
   */
  public MyDate(int day, int month, int year) throws IllegalArgumentException {
    // check if months are valid
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Month must be between 1 and 12");
    }
    // check if years are valid
    else if (year < 0 || year > 9999) {
      throw new IllegalArgumentException("Year must be positive or below 9999");
    }
    // belows check if days are valid
    // condition that those months have 31 days
    else if (is31Month(month)) {
      // check if those months have 31 days
      if (day < 1 || day > 31) {
        throw new IllegalArgumentException("Day must be between 1 and 31");
      }
    }
    // condition that those months have 30 days
    else if ((!is31Month(month)) && month != 2) {
      // check if those months have 30 days
      if (day < 1 || day > 30) {
        throw new IllegalArgumentException("Day must be between 1 and 30");
      }
    }
    // condition that month is Feb
    else {
      // condition that the year is a leap year
      if (isLeapYear(year)) {
        if (day < 1 || day > 29) {
          throw new IllegalArgumentException("Day must be between 1 and 29");
        }
      }
      // condition that the year is not a leap year
      else {
        if (day < 1 || day > 28) {
          throw new IllegalArgumentException("Day must be between 1 and 28");
        }
      }
    }
    // assign the day value to the day integer in the class
    this.day = day;
    // assign the month value to the month integer in the class
    this.month = month;
    // assign the year value to the year integer in the class
    this.year = year;
  }

  /**
   * function isLeapYear check whether the given integer is a leap year.
   *
   * @param year is the given year.
   * @return the boolean value whether the given year is a leap year.
   */
  private boolean isLeapYear(int year) {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
  }


  /**
   * function is 31 Day month check whether the month given has only 31 days.
   *
   * @param month is the given month value.
   * @return the boolean value whether the given month have 31 days.
   */
  private boolean is31Month(int month) {
    return (month == 1 || month == 3 || month == 5 || month == 7
            || month == 8 || month == 10 || month == 12);
  }

  /**
   * the function advance does advance the date by the given number of days,
   * which cannot over Dec 31, 9999
   * or reduce the date by the given number of days, which will stop at
   * Jan 1, 0000.
   *
   * @param days is the day increase or reduce.
   */
  public void advance(int days) {
    int currentDay = this.day + days;
    int dayInCurrentMonth = daysInThatMonth(this.month, this.year);
    // condition that the day pass the maximum date in this month
    if (currentDay > dayInCurrentMonth) {
      this.day = 0;
      monthAdv();
      advance(currentDay - dayInCurrentMonth);
    }
    // condition that the day is not enough to reduce in this month
    else if (currentDay < 1) {
      // reduce month if it's not 0000 or it's not in Jan
      if (this.year != 0 || this.month != 1) {
        monthRdc();
        this.day = daysInThatMonth(this.month, this.year);
        advance(currentDay);
      } else {
        this.day = 1;
      }
    } else {
      this.day += days;
    }
  }

  /**
   * monthAdv is the function that advancing month by 1
   * and throw an error over after Dec 9999.
   */
  private void monthAdv() {
    this.month += 1;
    if (this.month > 12) {
      this.month -= 12;
      this.year += 1;
    }
    if (this.year > 9999) {
      throw new IllegalArgumentException("Year must not be over 9999");
    }
  }

  /**
   * monthRdc is the function that reducing month
   * by 1 and stop reducing at Jan 0000.
   */
  private void monthRdc() {
    this.month -= 1;
    // return the month to 12 and decrease year by one if month is less than 1
    if (this.month < 1) {
      // reduce the year by one only if the year is above 0000
      if (this.year > 0) {
        this.month = 12;
        this.year -= 1;
      } else {
        this.month += 1;
      }

    }
  }

  /**
   * this function intake month and year and return
   * how many days should be in that month.
   *
   * @param month is the month int.
   * @param year  is the year int.
   * @return the number of days in that month in that year.
   */
  private int daysInThatMonth(int month, int year) {
    if (is31Month(month)) {
      return 31;
    } else {
      if (month == 2) {
        if (isLeapYear(year)) {
          return 29;
        } else {
          return 28;
        }
      } else {
        return 30;
      }
    }
  }

  /**
   * getDay the function that return the date value.
   *
   * @return the day value in MyDate.
   */
  public int getDay() {
    int return_day;
    return_day = this.day;
    return return_day;
  }

  /**
   * getMonth is the function that return the month value.
   *
   * @return the month value in MyDate.
   */
  public int getMonth() {
    int return_month;
    return_month = this.month;
    return return_month;
  }

  /**
   * getYear is the function that return the year value.
   *
   * @return the year value in MyDate.
   */
  public int getYear() {
    int return_year;
    return_year = this.year;
    return return_year;
  }

  /**
   * toString is the function that override toString Method by returning
   * a string value format in YYYY-MM-DD using string.format method.
   *
   * @return the string value in YYYY-MM-DD format.
   */
  @Override
  public String toString() {
    return String.format("%04d", this.year)
            + "-" + String.format("%02d", this.month)
            + "-" + String.format("%02d", this.day);
  }
}
