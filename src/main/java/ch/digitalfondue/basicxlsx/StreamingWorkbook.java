/**
 * Copyright © 2018 digitalfondue (info@digitalfondue.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.digitalfondue.basicxlsx;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;

public class StreamingWorkbook extends AbstractWorkbook implements Closeable, AutoCloseable {

    private final OutputStream os;
    private boolean hasEnded;

    public StreamingWorkbook(OutputStream os) {
        this.os = os;
    }

    @Override
    public void close() throws IOException {
        if (!hasEnded) {
            end();
        }
        os.close();
    }

    public void withSheet(String name, Stream<Cell[]> rows) {
    }

    public void end() {
        //TODO: add here all the metadata files
        if (hasEnded) {
            throw new IllegalStateException("already ended");
        } else {
            hasEnded = true;
        }
    }


/*
    static void test() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             StreamingWorkbook workbook = new StreamingWorkbook(baos)) {

            workbook.withSheet("test", Stream.empty());
            workbook.withSheet("test2", Stream.empty());
            workbook.end();//<- optional if close is called
        }
    }
*/
}
