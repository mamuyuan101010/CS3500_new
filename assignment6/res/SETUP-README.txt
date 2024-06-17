***No additional files are REQUIRED to run this program if you would like to use the API or any
AlphaVantage API csv files stored locally on your computer. However, if you would like to use
offline data in our program without ever calling an API, we have provided some files for you***

---------------------------------------------------------------------------------------------------
To access them with your jar file:
  1. From the res folder of this submission, copy the jar file and all files in the res/Stocks
     folder into a new common folder somewhere on your laptop.
  2. In that new folder you created, you should have the jar file and various csv files labeled
     with their ticker symbols.
  3. Once you run the jar file from this new folder on your terminal (powershell/shell), you should
     be able to feed in the files when prompted by just typing GOOG or AAPL for example.
---------------------------------------------------------------------------------------------------

1. From your terminal (powershell/shell), navigate to the folder at which your jar and data files
   are stored.

2. Once you are in that directory, type "java -jar Assignment4.jar"

3. After you have typed that, the program should be running as you will
   be prompted with the welcome message and a menu of options

-------------------------------------------------------------------------------------------------
SPECIFIC INSTRUCTIONS TO RUN A DEMO (assuming you pick up from step 3 and still have it running)
--------------------------------------
4a. Type in "4" (to create a portfolio)
5a. Type in "portfolio1" (to name the portfolio)
6a. Type in "5" (to add a stock to a portfolio)
7a. Type in "portfolio1" (portfolio to which you would like to add stocks)
8a. Type in "GOOG" (google stock)
9a. Type in "2022" year
10a. Type in "5" year
11a. Type in "3" year
12a. Type in "7" (add 7 shares of google stock to portfolio1)
13a. Type in "API" (utilize api to fetch data)
     Repeat steps 6-13 twice more to add 2 more stocks
     (replacing steps 8 and 9 with any valid ticker symbol and quantity you like on any date)
     (Ex. Add 2 shares of AAPL on 2023-6-3 and 3 shares of MSFT on 2024-5-29)

OR THROUGH FILES:
4b. Type in "4" (to create a portfolio)
5b. Type in "portfolio2" (to name the portfolio)
6b. Type in "5" (to add a stock to a portfolio)
7b. Type in "portfolio2" (portfolio to which you would like to add stocks)
8b. Type in "NVDA" (nvidia stock)
9b. Type in "2022" year
10b. Type in "5" year
11b. Type in "3" year
12b. Type in "3" (add 3 shares of nvidia stock to portfolio2)
13b. Type in "File" (utilize local csv file to fetch data)
     Type in the absolute/relative path to a csv file, or use our provided files
     (Ex. "NVDA" or "C:/Users/name/Downloads/daily_NVDA.csv")
     Repeat steps 6-13 once more to add 2 more stock this way
     (replacing steps 8 and 9 with any valid ticker symbol and quantity you like)
     (Ex. Add 5 shares of GOOG through "GOOG" and "5" on 2023-6-3
      & 13 shares of AAPL through "AAPL" and "13" on 2024-5-29)



TO QUERY VALUE OF PORTFOLIO ON A SPECIFIC DATE
18. Type in "9" (to get value)
19. Type in "portfolio1" or "portfolio2" (specify which one you want value of)
20. Type in date you want (ex. "2024-05-29")
21. Repeat for any second date you would like (ex. 2023-7-21)
--------------------------------------------------------------------------------

List of stocks supported:

-All stocks available on the AlphaVantage API
 (https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=demo)

-All stocks for which the user has a local csv file following the same format as
 csv file from AlphaVantage API for stock information

-Stocks whose files we have provided in our res/Stocks folder
-AAPL, AMZN, DAL, GOOG, MSFT, NVDA, SHEL, SONY, SP, TESLA

Date Restrictions:
No data for dates that are not available from the AlphaVantage API for each stock
unless the user has their own local file of dates, at which point the restriction
depends on their information