package gov.nih.nlm.ncbi.seqr.tokenizer;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Simple Tests to ensure this RawSequence2Tokenizer is working
 * //http://dmitrykan.blogspot.com/2014/06/low-level-testing-your-lucene.html
 */
public class TestRawSequence2Tokenizer extends BaseTokenStreamTestCase {
    public void testPositionIncrementsSingleTerm() throws IOException {
        Tokenizer stream = new RawSequence2Tokenizer(newAttributeFactory(), new StringReader("ABCDEFGHIJKLMN"), 5);
        assertTokenStreamContents(stream, new String[]{"ABCDE", "FGHIJ"});
    }
}


