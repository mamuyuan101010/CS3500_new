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

  public void advance(int days) {

  }

  public String toString() {

    return "";
  }
}
