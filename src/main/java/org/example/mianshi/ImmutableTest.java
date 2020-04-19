package org.example.mianshi;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

public class ImmutableTest {


    public static void main(String[] args) {
        Set<Bar> bars = Sets.newHashSet(new Bar());
        bars.add(new Bar());
        System.out.println(bars);

        Foo foo = new Foo(bars);
        Set<Bar> bar = foo.bars;
        System.out.println(bar.size());
//        bar.add(new Bar());throw execption


    }

    static class Bar {
        public Bar() {
        }
    }

    static class Foo {
        Set<Bar> bars;

        Foo(Set<Bar> bars) {
            this.bars = ImmutableSet.copyOf(bars); // defensive copy!
        }
    }

}
