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

package gov.nih.nlm.ncbi.seqr.tokenizer;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.AttributeFactory;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * This tokenizer is for tokenizing protein sequences into indexing tokens
 */

public final class RawSequence2Tokenizer extends Tokenizer {

    //private final static Logger logger = LoggerFactory.getLogger(SequenceTokenizer.class);
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    // no position attr:
    // private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    private final int nmerSize;
    private final BufferedReader br;

    /**
     * creates a new SequenceTokenizer returning tokens
     */
    public RawSequence2Tokenizer(Reader input, int nmerSize) {
        this(DEFAULT_TOKEN_ATTRIBUTE_FACTORY, input, nmerSize);
    }

    /**
     * creates a new SequenceTokenizer returning tokens
     */
    public RawSequence2Tokenizer(AttributeFactory factory, Reader input, int nmerSize) {
        super(factory, input);
        this.nmerSize = nmerSize;
        this.br = new BufferedReader(input);
    }


    @Override
    public boolean incrementToken() {
        clearAttributes();
        char[] token = new char[nmerSize]; //generate 5-mers
        int indexValue = 0;
        TokenKey tk = new TokenKey();
        String tStr = "";
        try {
            while (br.read(token, 0, nmerSize) >= 4) {
                tStr = String.valueOf(token).trim();
                termAtt.setEmpty();
                if (tStr.length() == nmerSize) {
                    termAtt.append(tStr.toUpperCase());
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("unable to process tokens ");
        }
        return false;//no more tokens
    }
}
