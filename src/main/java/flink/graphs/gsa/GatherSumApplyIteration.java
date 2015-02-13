/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package flink.graphs.gsa;

import flink.graphs.Edge;
import flink.graphs.Vertex;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

import java.io.Serializable;

public abstract class GatherSumApplyIteration<K extends Comparable<K> & Serializable, VV extends Serializable,
        EV extends Serializable, MO extends Serializable> implements
        MapFunction<Tuple3<Vertex<K, VV>, Edge<K, EV>, Vertex<K, VV>>, Tuple2<K, MO>> {

    public abstract Tuple2<K, MO> gather(Tuple3<Vertex<K, VV>, Edge<K, EV>, Vertex<K, VV>> triplet);

    @Override
    public Tuple2<K, MO> map(Tuple3<Vertex<K, VV>, Edge<K, EV>, Vertex<K, VV>> triplet) throws Exception {
        return gather(triplet);
    }
}