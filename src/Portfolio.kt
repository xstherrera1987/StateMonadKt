/** simple representation of a stocks portfolio.
 *  Map<String, Double> maps stock-name to the quantity owned
 */
typealias Stocks = Map<String, Double>

/** buys `dollarAmount` (dollars) of the stock with given stockName
 * @return the number of purchased stocks (not the total owned)
 */
fun buy(stockName: String, dollarAmount: Double, portfolio: Stocks): Pair<Double, Stocks> {
    val quantityPurchased = dollarAmount / Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned + quantityPurchased

    return Pair(quantityPurchased, updatedPortfolio)
}

/** sells a quantity of stocks of the given name
 * @return the amount of dollars earned by the selling operation (owned)
 */
fun sell(stockName: String, sellQuantity: Double, portfolio: Stocks): Pair<Double, Stocks> {
    val revenue = sellQuantity * Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned - sellQuantity

    return Pair(revenue, updatedPortfolio)
}


/** @return the quantity of stocks owned for name */
fun get(name: String, portfolio: Stocks): Double = portfolio[name] ?: 0.0
