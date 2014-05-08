/*
 * Copyright 2014 Malte Schütze
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.boreeas.riotapi.rtmp.p2.serialization.amf3;

import net.boreeas.riotapi.rtmp.p2.AmfWriter;
import net.boreeas.riotapi.rtmp.p2.serialization.AmfSerializer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created on 5/3/2014.
 */
public class Amf3IntVectorSerializer implements AmfSerializer<int[]> {
    private AmfWriter writer;

    public Amf3IntVectorSerializer(AmfWriter writer) {
        this.writer = writer;
    }

    @Override
    public void serialize(int[] ints, OutputStream out) throws IOException {
        writer.serializeAmf3(ints.length << 1 | 1);
        out.write(1);   // Fixed size

        for (int i: ints) {
            out.write(i >> 24);
            out.write(i >> 16);
            out.write(i >> 8);
            out.write(i);
        }
    }
}
