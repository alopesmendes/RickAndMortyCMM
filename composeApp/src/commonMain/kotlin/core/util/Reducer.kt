package core.util

import kotlin.reflect.KFunction1

interface Reducer<State : Reducer.ViewState, Intent : Reducer.ViewIntent, Effect : Reducer.ViewEffect> {
    interface ViewState

    interface ViewIntent

    interface ViewEffect

    fun reduce(previousState: KFunction1<(State) -> State, Unit>, event: Intent)
}