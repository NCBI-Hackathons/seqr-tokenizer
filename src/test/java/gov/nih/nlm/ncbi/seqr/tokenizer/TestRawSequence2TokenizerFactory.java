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

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoader;

import java.io.StringReader;

/**
 * Simple Tests to ensure this factory is working
 */
public class TestRawSequence2TokenizerFactory extends BaseTokenStreamFactoryTestCase {


    public void testFactory2() throws Exception {

        ResourceLoader loader = new ClasspathResourceLoader(getClass());
        assertTrue("loader is null and it shouldn't be", loader != null);

        RawSequence2TokenizerFactory sf = (RawSequence2TokenizerFactory) tokenizerFactory("rawsequence2", "nmer", "5");
        for (int i = 0; i <= 1000; i++) {
            TokenStream stream = sf.create(newAttributeFactory(), new StringReader("ABCDEFGHIJKLMN"));

            assertTokenStreamContents(stream, new String[]{"ABCDE", "FGHIJ"});
            TokenStream stream2 = sf.create(newAttributeFactory(), new StringReader("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
            assertTokenStreamContents(stream2, new String[]{"AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA"});
        }
    }
}
