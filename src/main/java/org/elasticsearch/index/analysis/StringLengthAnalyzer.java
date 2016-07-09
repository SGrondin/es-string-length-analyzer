package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;


public final class StringLengthAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String field) {
    	Tokenizer source = new KeywordTokenizer();
    	TokenStream result = new StringLengthTokenFilter(source);
        return new TokenStreamComponents(source, result);
    }
}
