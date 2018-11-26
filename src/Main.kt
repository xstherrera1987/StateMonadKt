/**
 * Demo of State-Monad as described in:
 *     http://rcardin.github.io/design/programming/fp/monad/2018/11/22/and-monads-for-all-state-monad.html
 *
 */
var portfolio: Stocks = Stocks()
const val lifeSavings = 105.00

fun main(args : Array<String>) {

    buy("google", lifeSavings)(portfolio).let { (googleStocks: Double, newPortfolio: Stocks) ->
        portfolio = newPortfolio
        println("bought $googleStocks Google stocks at \$${Prices("google")} each, with \$$lifeSavings")
    }

    move("google", "microsoft")(portfolio).let { result: Pair< Pair<Double, Double>, Stocks> ->
        portfolio = result.second
        val (googleStocks, microsoftStocks) = result.first
        println("sold $googleStocks Google stocks and with revenue purchased $microsoftStocks Microsoft stocks")
    }
}
