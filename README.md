# seqr tokenizer solr plugin

##usage: 
 
          <analyzer>
                <tokenizer class="gov.nih.nlm.ncbi.seqr.tokenizer.RawSequence2TokenizerFactory" nmer="5"/>
                <filter class="gov.nih.nlm.ncbi.seqr.tokenizer.RemoveDuplicatesTokenFilterIgnorePositionFactory"/>
                <filter class="solr.LengthFilterFactory" min="5" max="5"/>
          </analyzer>