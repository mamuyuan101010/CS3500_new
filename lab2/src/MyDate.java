public class MyDate {
  int day;
  int month;
  int year;

  public MyDate(int day, int month, int year) throws IllegalArgumentException {
    // check if months are valid
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Month must be between 1 and 12");
    }
    // check if years are valid
    else if (year < 0) {
      throw new IllegalArgumentException("Year must be positive");
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
    this.day = day;
    this.month = month;
    this.year = year;
  }

  // function isLeapYear check whether the given integer is a leap year
  private boolean isLeapYear(int year) {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
  }

  // function is 31 Day month check whether the month given has only 31 days
  private boolean is31Month(int month) {
    return (month == 1 || month == 3 || month == 5 || month == 7
            || month == 8 || month == 10 || month == 12);
  }

  /**
   * the function advance does advance the date by the given number of days
   * @param days is the day increase or reduce
   */
  public void advance(int days) {
    int currentDay = this.day + days;
    int dayInCurrentMonth = daysInThatMonth(this.month, this.year);
    if (currentDay > dayInCurrentMonth) {
      this.day = 0;
      monthAdv();
      advance(currentDay - dayInCurrentMonth);
    }
    else if (currentDay < 1) {
      monthRdc();
      this.day = daysInThatMonth(this.month, this.year);
      advance(currentDay + dayInCurrentMonth);
    }
    else {
      this.day += days;
    }
  }

  // function advance month by 1
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

  // function reduce month by 1
  private void monthRdc() {
    this.month -= 1;
    // return the month to 12 and decrease year by one if month is less than 1
    if (this.month < 1) {
      this.month = 12;
      // reduce the year by one only if the year is above 0000
      if (this.year > 0) {
        this.year -= 1;
      }
    }
  }

  /**
   * this function intake month and year and return
   * how many days should be in that month
   * @param month is the month int
   * @param year is the year int
   * @return the number of days in that month in that year
   */
  private int daysInThatMonth(int month, int year) {
    if (is31Month(month)) {
      return 31;
    }
    else {
      if (month == 2) {
        if (isLeapYear(year)) {
          return 29;
        }
        else {
          return 28;
        }
      }
      else {
        return 30;
      }
    }
  }

  public int getDay() {
    int return_day;
    return_day = this.day;
    return return_day;
  }
  public int getMonth() {
    int return_month;
    return_month = this.month;
    return return_month;
  }
  public int getYear() {
    int return_year;
    return_year = this.year;
    return return_year;
  }

  public String toString() {
    return String.format("%04d", this.year) +
            "-" + String.format("%02d",this.month) +
            "-" + String.format("%02d",this.day);
  }
}
