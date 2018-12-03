/** move all money invested in one stock into investments for another
 *
 * 1. Sell all the stocks for a given company
 * 2. Using the sell’s revenue, buying stocks of another company
 * 3. Return the number of stocks of the first type owned, and the quantity of the newly purchased stocks
 *
 * @return Transaction that returns the number of sold-stocks previously owned, and the quantity of new-stocks purchased
 */
fun move(fromStockName: String, toStockName: String): Transaction< Pair<Double,Double>> =
    get(fromStockName).flatMap { originallyOwned: Double ->
        sell(fromStockName, originallyOwned).flatMap { revenue: Double ->
            buy(toStockName, revenue).map { purchased: Double ->
                Pair(originallyOwned, purchased)
            }
        }
    }
