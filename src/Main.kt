/**  Kotlin code for State-Monad as described in:
 *     http://rcardin.github.io/design/programming/fp/monad/2018/11/22/and-monads-for-all-state-monad.html
 */

fun main(args : Array<String>) {
    val lifeSavings = 105.00
    val portfolio: Stocks = Stocks()

    buy("google", lifeSavings).flatMap { googleStocks: Double ->

        println("bought $googleStocks Google stocks at \$${Prices("google")} each, with \$$lifeSavings")

        move("google", "microsoft").map { (soldStocks: Double, microsoftStocks: Double) ->

            println("sold $soldStocks Google stocks and with revenue purchased $microsoftStocks Microsoft stocks")
        }
    }(portfolio)
}
