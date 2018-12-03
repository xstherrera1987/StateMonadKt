/** simple representation of a stocks portfolio.
 *  Map<String, Double> maps stock-name to the quantity owned
 */
typealias Stocks = Map<String, Double>

/** constructor for our Stocks type */
fun Stocks(): MutableMap<String, Double> = mutableMapOf()


/** state monad on Stocks */
typealias Transaction<Result> = StateMonadTrait<Stocks, Result>

/** buys `dollarAmount` (dollars) of the stock with given stockName
 * @return Transaction that returns the number of purchased stocks (not the total owned) given a portfolio
 */
fun buy(stockName: String, dollarAmount: Double): Transaction<Double> = Transaction.lambdaMonad { portfolio: Stocks ->
    val quantityPurchased = dollarAmount / Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned + quantityPurchased

    Transaction.unit<Stocks, Double>(quantityPurchased)(updatedPortfolio)
}

/** sells a quantity of stocks of `stockName`
 * @return Transaction that returns the amount of dollars earned by the selling operation (owned) given a portfolio
 */
fun sell(stockName: String, sellQuantity: Double): Transaction<Double> = Transaction.lambdaMonad { portfolio: Stocks ->
    val revenue = sellQuantity * Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned - sellQuantity

    Transaction.unit<Stocks, Double>(revenue)(updatedPortfolio)
}

/** @return Transaction that returns the quantity of stocks owned for `stockName` given a portfolio */
fun get(stockName: String) : Transaction<Double> = Transaction.lambdaMonad { portfolio: Stocks ->
    Transaction.unit<Stocks, Double>(portfolio[stockName]?: 0.0)(portfolio)
}
