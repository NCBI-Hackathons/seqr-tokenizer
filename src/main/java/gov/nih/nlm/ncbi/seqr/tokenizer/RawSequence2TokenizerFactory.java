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

import org.apache.lucene.analysis.util.AbstractAnalysisFactory;
import org.apache.lucene.analysis.util.MultiTermAwareComponent;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


/**
 * Factory for {@link RawSequence2TokenizerFactory}.s
 * <pre class="prettyprint">
 * &lt;fieldType name="sequence" class="solr.TextField" omitNorms="true"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="gov.nih.nlm.ncbi.seqr.tokenizer.RawSequence2TokenizerFactory" nmer="5" /&gt;
 *     &lt;filter class="gov.nih.nlm.ncbi.seqr.tokenizer.RemoveDuplicatesTokenFilterIgnorePositionFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;
 * </pre>
 */

public class RawSequence2TokenizerFactory extends TokenizerFactory implements MultiTermAwareComponent {

    // private final static Logger logger = LoggerFactory.getLogger(RawSequence2TokenizerFactory.class);
    public static final String NMER = "nmer";
    private final int nmerSize;


    public AbstractAnalysisFactory getMultiTermComponent() {
        return new RawSequence2TokenizerFactory(new HashMap<String, String>(getOriginalArgs()));
    }

    /**
     * Creates a new SequenceTokenizerFactory
     */
    public RawSequence2TokenizerFactory(Map<String, String> args) {
        super(args);
        nmerSize = getInt(args, NMER, 5);
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("Unknown parameters: " + args);
        }
    }

    /**
     * Split the input using configured pattern
     */
    @Override
    public RawSequence2Tokenizer create(final AttributeFactory factory, final Reader in) {
        return new RawSequence2Tokenizer(in, nmerSize);
    }

}
