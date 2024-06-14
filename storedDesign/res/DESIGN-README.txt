text description of this design:

-------------------------------------
Design changes:
    Changed return type of queryStock() from Stock to Investment to avoid return of concrete class
--------------------------------------

------------------------------------------------------------- |
The design for this application utilizes the MVC architecture |
------------------------------------------------------------- |

Model
------
The Investment interface:
    We chose to use an Investment interface to allow this project to be open to extension.
    We kept in mind that later on, it may be feasible to perform similar operations with other
    forms of investments such as bonds, ETFs, Mutual Index Funds, Cryptocurrency, foreign currency,
    etc. So, someone can create classes for those under a common investment interface as they would
    likely all have similar methods.

The Stock class:
    Going off of the above point, This stock class represents the objective of this assignment
    which was centered around stocks. We chose to represent a stock through its ticker symbol
    and all of its historical data. The historical data is stored as a List of StockData objects.
    One StockData object represents the high, low, open, and close prices and volume for a certain
    stock on a specific date. The List will have data for all timestamps that can be retrieved. We
    utilized abstraction in the methods by creating a helper method which abstracted the shared
    logic of identifying which timestamps/dates needed to be analyzed.
    The StockData objects are nested inside this class, which we will explain in the next section.

The StockData class:
    We chose to use a nested static class as the only class which needs stock data information is
    the Stock class itself. There is no reason for this object to exist other than for the Stock
    objects. This class represents one object holding all the prices data at one point in time.
    As mentioned above, the Stock object holds a list of these objects.

The StockDataSource interface:
    This interface represents the operation of fetching data for stock objects. As data can be
    obtained from a variety of sources (files, API, manual, etc), we used an interface as all of
    these sources will consist of the operation of retrieving data. Additionally, using an
    interface means that in the future, we can easily add more sources that are unified with the
    ones we currently have. So essentially, through this interface, our program supports multiple
    sources.

The AbstractDataSource class:
    This class is abstract as it represents the shared logic in fetching data from any source.
    From any source, the pattern follows: check if the data is already stored locally (CacheManager
    which we'll talk about shortly), scan the data source, and then construct StockData objects
    from that data source. The only public method is fetchData() as the other steps are just
    helpers in this overall process. From this class, each extended class will utilize fetchNewData
    for the specific and unique steps.

The APIStockDataSource and FileStockDataSource classes:
    APISDS class represents getting data from the AlphaVantage API. FileSDS class represents
    getting data from a csv file containing the same information as produced by AlphaVantage.
    As stated above, user has multiple ways of getting data.

    We started with just the APISDS, but then implemented FileSDS to support offline functionality.
    A user simply needs to input a locally downloaded file to access all the functions.

The CacheManager class:
    This class is something unique that we added. It represents a local cache of all previously
    queried stock data. When talking about APIs, there are only so many requests that can be made
    before a limit is hit. Why waste usages contributing to that limit on data that was already
    retrieved? This way, we improve efficiency as locally stored data is already available to
    produce, and address the issue of overloading an API. This class will store fetched data from
    any source, files, API, or any other future source implemented.

    For this class, we utilized the singleton pattern. The singleton pattern only allows one
    instance of an object to exist at a time, so that pattern is perfect here. We only want one
    local cache. All queries should be sent to one centralized location for easy and fast recovery.
    Whenever a user wants to fetch any data, all requests will first be checked against the local
    cache to see if extra work is needed at all. Additionally, this class supports the feature
    of offline usage.

The Portfolio interface:
    This interface represents the operations for any portfolio a user may construct. When thinking
    about the future, many forms of portfolios can exist, so it is best to have an interface
    representing common functionality between all those implementations.

The SimplePortfolio class:
    For example, one instance can be a simple portfolio that only holds stock objects. This class
    represents that and supports operations of adding stocks, and getting value in regard to
    stocks added. It is a simple portfolio. However, when regarding our Investment interface, and
    how we specified that in the future, different forms of investments can be made possible, we
    wanted to allow the ability to create portfolios representing that. However, we do not want
    users to override or mess up the working code for a simple portfolio. So it is a final class
    that is meant to be used for composition, not inheritance. For example, if a user wants a
    portfolio of bonds and stocks, they can make a new ComplexPortfolio class that uses a
    SimplePortfolio delegate object. In their methods, whenever they want to address adding stocks
    or getting value of stocks, they can delegate. Then they can override the addInvestment and
    getValueOf methods to additionally include logic for adding and getting the value of bonds.

    Users can add new functionality to portfolios through composition, if they want to hold more
    than just stocks in the future.

The PortfolioManager class:
    This class is the main "Model" class. This model is what will be used for the application
    It stores a map of portfolios associated with a name. Each portfolio has a map of stocks.
    It is very easy to delegate to the Stock class for the features requiring analysis of a
    specific stock.

The IModel:
    This is the interface for classes representing models. Future model classes with new
    functionalities can implement an interface that extends this IModel interface.
------------------------------------------------------------------------------------------

Controller
-----------
The InvestmentController class:
    Similar to the PortfolioManager class, this is the main controller class for this program.
    This controller follows the extensible command design pattern by creating a Map of known
    commands. These commands are all delegated to classes that are unified under a common
    interface. With this design, adding new functionality or a new command is as simple as
    making a new class and adding that class's command to the map of known commands. Additionally,
    the code is much easier to read without switch statements.

The AbstractCommands class:
    Relating to above, each command was delegated to a different class. However, many of these
    commands shared common functionality such as prompting the user for a stock, a source, or
    certain dates. So the whole action of prompting a common message and needing to scan that input
    was abstracted to AbstractCommands.

The StocksCommands interface:
    This is the interface that unifies all the commands. It just has one method called execute
    as a command just needs to execute its function. It takes in model and view for that method as
    Each command not only takes in an input to send to the model, but also displays custom
    messages to the user based on their action.

All the remaining classes in the Controller package are the commands. They each display a
custom prompt and then call specific methods on the model.

More functionality can be added by simply implementing the StocksCommands interface to a new class
for each new command.
------------------------------------------------

View
-----

The IView interface:
    This interface represents operations for any view classes that should display some sort of
    output to the user. This interface has a barebones set of operations that every view class
    must have, such as the welcome message, goodbye message, and a menu. Then the fourth method
    is taking in a custom message, which is used for the commands which display specific prompts
    and results.

The TextView class:
    This is the main view class for this application as of right now. It is a text based view that
    is interactive with the user's actions.
---------------------------------------------------------------------------------------------------

Features:

-Typos do not cause the program to crash and exit the program, they are handled appropriately
 letting the user know they entered something invalid.

-No long unwieldy inputs needed by user

-No fractional amount of shares allowed to be bought, only whole shares as reinforced by int
 parameter in the method that handles adding stocks.

-Program not dependent on internet connection

-Lowers number of API queries made through local cache

