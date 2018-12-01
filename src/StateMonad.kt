/** state monad for a result value */
fun <Result, State> unit(result: Result): StatefulComputation<Result, State> = { state: State ->
    Pair(result, state)
}

/** (composable) state monad for mapping between type A and B */
fun <A, B, State> map(stateMonad: StatefulComputation<A, State>, transform: (A) -> B): StatefulComputation<B, State> =
    { state: State ->
        val (intermediaryResult, newState) = stateMonad(state)
        val result: B = transform(intermediaryResult)
        unit<B, State>(result)(newState)
    }

/** (composable) state monad for mapping between type A and B, resulting in a flat collection (no sub-collections) */
fun <A, B, State> flatMap(
    stateMonad: StatefulComputation<A, State>,
    transform: (A) -> StatefulComputation<B, State>
): StatefulComputation<B, State> = { state: State ->
    val (intermediaryResult, newPortfolio) = stateMonad(state)
    transform(intermediaryResult)(newPortfolio)
}
