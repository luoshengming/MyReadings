package com.fpinjava.state.exercise12_10;

import com.fpinjava.common.*;

public class StateMachine<A, S> {

    Function<A, State<S, Nothing>> function;

    public StateMachine(List<Tuple<Condition<A, S>, Transition<A, S>>> transitions) {
        function = a -> State.sequence(m ->
                Result.success(new StateTuple<>(a, m)).flatMap((StateTuple<A, S> t) ->
                        transitions.filter((Tuple<Condition<A, S>, Transition<A, S>> x) ->
                                x._1.apply(t)).headOption().map((Tuple<Condition<A, S>, Transition<A, S>> y) ->
                                y._2.apply(t))).getOrElse(m));
    }

    public State<S, S> process(List<A> inputs) {
        List<State<S, Nothing>> a = inputs.map(function);
        State<S, List<Nothing>> b = State.sequence(a);
        return b.flatMap(x -> State.get());
    }
}
