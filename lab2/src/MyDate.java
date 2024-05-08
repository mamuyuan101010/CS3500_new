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
    return (month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 11);
  }

<<<<<<< HEAD
  /**
   * the function advance does advance the date by the given number of days
   * @param days
   */
  public void advance(int days) {
    dayAdv(days);
  }

  /**
   * this function auto-correct days
   * @param days
   */
  private void dayAdv(int days) {
    int currentDay = this.day;
    this.day += days;
    // condition for months that have 31 days
    if (is31Month(this.month)) {
      if (this.day > 31) {
        this.day = 1;
        this.month += 1;
        monthAdv(this.month);
        advance(currentDay - 31);
      }
    }
    // condition for Feb
    else if (this.month == 2) {
      // condition for Feb in leap year
      if (isLeapYear(this.year)) {
        if (this.day > 29) {
          this.day -= 29;
          this.month += 1;
          monthAdv(this.month);
          advance(days - 29);
        }
      }
      // condition for Feb in non-leap year
      else {
        if (this.day > 28) {
          this.day -= 28;
          this.month += 1;
          monthAdv(this.month);
          advance(days - 28);
        }
      }
    }
    // condition that months have 30 days
    else {
      if (this.day > 30) {
        this.day -= 30;
        this.month += 1;
        monthAdv(this.month);
        advance(days - 30);
      }
    }
  }

  // function that make the month correct
  private void monthAdv(int month) {
    this.month += month;
    if (this.month > 12) {
      this.month -= 12;
      this.year += 1;
    }
    if (this.year > 9999) {
      throw new IllegalArgumentException("Year must not be over 9999");
    }
  }

  public int getDay() {
    return this.day;
  }
  public int getMonth() {
    return this.month;
  }
  public int getYear() {
    return this.year;
=======
  public void advance(int days) {

>>>>>>> origin/main
  }

  public String toString() {

<<<<<<< HEAD
    return String.format("%04d", this.year) +
            "-" + String.format("%02d",this.month) +
            "-" + String.format("%02d",this.day);
=======
    return "";
>>>>>>> origin/main
  }
}
