/** simple representation of a stocks portfolio.
 *  Map<String, Double> maps stock-name to the quantity owned
 */
typealias Stocks = Map<String, Double>

fun Stocks(): MutableMap<String, Double> = mutableMapOf()


/** partially applied stateful computation on Stocks
 * essentially:
 *  (Stocks) -> Pair<Result, Stocks>
 */
typealias Transaction<Result> = StatefulComputation<Result, Stocks>

/** buys `dollarAmount` (dollars) of the stock with given stockName
 * @return Transaction that returns the number of purchased stocks (not the total owned) given a portfolio
 */
fun buy(stockName: String, dollarAmount: Double) : Transaction<Double> = { portfolio: Stocks ->
    val quantityPurchased = dollarAmount / Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned + quantityPurchased

    apply(quantityPurchased)(updatedPortfolio)
}

/** sells a quantity of stocks of the given name
 * @return Transaction that returns the amount of dollars earned by the selling operation (owned) given a portfolio
 */
fun sell(stockName: String, sellQuantity: Double) : Transaction<Double> = { portfolio: Stocks ->
    val revenue = sellQuantity * Prices(stockName)
    val owned = portfolio[stockName] ?: 0.0

    val updatedPortfolio = portfolio.toMutableMap()
    updatedPortfolio[stockName] = owned - sellQuantity

    apply(revenue)(updatedPortfolio)
}

/** @return Transaction that returns the quantity of stocks owned for name given a portfolio */
fun get(name: String) : Transaction<Double> = { portfolio: Stocks ->
    apply(portfolio[name]?: 0.0)(portfolio)
}

//region combinator functions

/** @return Transaction that returns the pair of result and new portfolio, given a portfolio */
fun <A> apply(value: A): Transaction<A> = { portfolio: Stocks ->
    Pair(value, portfolio)
}

/** allows applying a function to instances of type-A contained in a generic data structure to produce another
 * data-structure with items of type-B
 *
 * @return Transaction that returns the result of applying some transform to the result of another transaction, given a portfolio
 */
fun <A, B> map(transaction: Transaction<A>, transform : (A) -> B) : Transaction<B> = { portfolio: Stocks ->
    val (intermediaryResult, newPortfolio) = transaction(portfolio)
    Pair(transform(intermediaryResult), newPortfolio) // equivalently: apply(transform(intermediaryResult))(portfolio)
}

/** the flattening version of `map` */
fun <A, B> flatMap(transaction: Transaction<A>, transform : (A) -> Transaction<B>) : Transaction<B> = { portfolio: Stocks ->
    val (intermediaryResult, newPortfolio) = transaction(portfolio)
    transform(intermediaryResult)(newPortfolio)
}
//endregion