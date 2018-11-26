/** simple representation of a stocks portfolio.
 *  Map<String, Double> maps stock-name to the quantity owned
 */
typealias Stocks = Map<String, Double>

/** buys `dollarAmount` (dollars) of the stock with given stockName
 * @return the number of purchased stocks (not the total owned)
 */
val buy : StatefulComputation2<String, Double, Double, Stocks> =
    { stockName: String, dollarAmount: Double, portfolio: Stocks ->
    val quantityPurchased = dollarAmount / Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned + quantityPurchased

    Pair(quantityPurchased, updatedPortfolio)
}

/** sells a quantity of stocks of the given name
 * @return the amount of dollars earned by the selling operation (owned)
 */
val sell : StatefulComputation2<String, Double, Double, Stocks> =
    { stockName: String, sellQuantity: Double, portfolio: Stocks ->

    val revenue = sellQuantity * Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned - sellQuantity

    Pair(revenue, updatedPortfolio)
}

/** @return the quantity of stocks owned for name */
val get : StatefulComputation1<String, Double, Stocks> = { name: String, portfolio: Stocks ->
    Pair(portfolio[name] ?: 0.0, portfolio)
}
