/**
 * a stateful computation is a function that takes some state and returns a value along with a new, updated state
 * AKA: state actions or state transitions
 */
typealias StatefulComputation<Result, State> = (State) -> Pair<Result, State>

/** @see StatefulComputation stateful computation on immutable data with 0 arguments */
typealias StatefulComputation0<R, S> = StatefulComputation<R, S>

/** @see StatefulComputation stateful computation on immutable data with 1 argument */
typealias StatefulComputation1<A1, R, S> = (A1, S) -> Pair<R, S>

/** @see StatefulComputation stateful computation on immutable data with 2 arguments */
typealias StatefulComputation2<A1, A2, R, S> = (A1, A2, S) -> Pair<R, S>

/** @see StatefulComputation stateful computation on immutable data with 3 arguments */
typealias StatefulComputation3<A1, A2, A3, R, S> = (A1, A2, A3, S) -> Pair<R, S>
