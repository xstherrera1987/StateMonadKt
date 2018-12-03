typealias StatefulComputation<Result, State> = (State) -> Pair<Result, State>

interface StateMonadTrait<State, Result> : StatefulComputation<Result, State> {

    val self: StateMonadTrait<State, Result>

    /** (composable) state monad for mapping between type A and B */
    fun <NewResult> map(
        transform: (Result) -> NewResult
    ): StateMonadTrait<State, NewResult> = lambdaMonad { state: State ->
        val (intermediaryResult, newState) = self.invoke(state)
        val result: NewResult = transform(intermediaryResult)
        unit<State, NewResult>(result)(newState)
    }

    /** (composable) state monad for mapping between type A and B, resulting in a flat collection (no sub-collections) */
    fun <NewResult> flatMap(
        transform: (Result) -> StatefulComputation<NewResult, State>
    ): StateMonadTrait<State, NewResult> = lambdaMonad { state: State ->
        val (intermediaryResult, newPortfolio) = self.invoke(state)
        transform(intermediaryResult)(newPortfolio)
    }

    companion object {
        /** instantiates a state monad for the given result */
        fun <State, Result> unit(result: Result): StateMonadTrait<State, Result> =
            object : StateMonadTrait<State, Result> {
                override val self = this
                override fun invoke(state: State): Pair<Result, State> = Pair(result, state)
            }

        /** instantiates a state monad for the given result, using a lambdaMonad */
        fun <State, Result> lambdaMonad(lambda: StatefulComputation<Result, State>): StateMonadTrait<State, Result> =
            object : StateMonadTrait<State, Result> {
                override val self = this
                override fun invoke(state: State): Pair<Result, State> = lambda.invoke(state)
            }
    }
}
