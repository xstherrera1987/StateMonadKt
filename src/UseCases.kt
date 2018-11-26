/** move all money invested in one stock into investments for another
 *
 * 1. Sell all the stocks for a given company
 * 2. Using the sell’s revenue, buying stocks of another company
 * 3. Return the number of stocks of the first type owned, and the quantity of the newly purchased stocks
 *
 * @return number of stocks of the first type owned, and the quantity of the newly purchased stocks and updated portfolio
 */
fun move(fromStockName: String, toStockName: String, portfolio: Stocks): Pair< Pair<Double,Double>, Stocks > {
    val originallyOwned = get(fromStockName)(portfolio).first
    val (revenue, newPortfolio) = sell(fromStockName, originallyOwned)(portfolio)
    val (purchased, veryNewPortfolio) = buy(toStockName, revenue)(newPortfolio)

    return Pair( Pair(originallyOwned, purchased), veryNewPortfolio)
}
