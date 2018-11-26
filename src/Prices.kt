/**
 * data-source for stock-prices
 *
 * "We imagine to live in a colored world, full of funny and unicorns,
 * so our portfolio always contains some stocks for the given name."
 *
 * @return price of a stock given its name
 */
fun Prices(name: String): Double {
    return when (name.toLowerCase()) {
        "google" -> 3.14159
        "microsoft" -> 1.95
        "facebook" -> 1.94
        "apple" -> 1.91
        "tesla" -> 1.87
        else -> 0.0
    }
}
