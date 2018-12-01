# StateMonadKt

State-Monad as described in [this article](http://rcardin.github.io/design/programming/fp/monad/2018/11/22/and-monads-for-all-state-monad.html).
Being a primarily Kotlin/Java developer now, I found the article _less-than-straightforward_ so I've pulled the code 
examples and written the analogous Kotlin code for each section (eg. phase) as the article progresses.  I'm hoping this
is relevant to someone as reactive architectures like 
[Model-View-Itent](https://proandroiddev.com/the-contract-of-the-model-view-intent-architecture-777f95706c1e)
and [Reactive Workflows](https://www.youtube.com/watch?v=KjoMnsc2lPo) become more common and 
more are exposed to the corresponding functional patterns.

## Branches
* phase-1: core logic, immutable
* phase-2: stateful computation
* phase-3: partial-application and Transaction
* phase-4: combinator functions
* phase-5: state monad
* phase-6: refactor to Trait
* master: end-result (?)

## Links
* ["...And Monads for (Almost) All: The State Monad" by Riccardo Cardin](http://rcardin.github.io/design/programming/fp/monad/2018/11/22/and-monads-for-all-state-monad.html)
* ["The State Monad: A Tutorial for the Confused" by Brandon.Simmons](http://brandon.si/code/the-state-monad-a-tutorial-for-the-confused/)
* ["Demistify the State Monad with Scala" by Patrick Di Loreto](http://patricknoir.blogspot.com/2014/12/demistify-state-monad-with-scala-12.html)
