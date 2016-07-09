package org.elasticsearch.index.analysis;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.CharsRefBuilder;


public final class StringLengthTokenFilter extends TokenFilter {

  private final CharTermAttribute charTermAttr = addAttribute(CharTermAttribute.class);

  public StringLengthTokenFilter(TokenStream tokenStream) {
    super(tokenStream);
  }

  @Override
  public final boolean incrementToken() throws IOException {
    if (this.input.incrementToken()) {
      String currentTokenInStream = this.input.getAttribute(CharTermAttribute.class).toString();
      this.charTermAttr.setEmpty().append(Integer.toString(currentTokenInStream.length()));
      return true;
    } else {
      return false;
    }
  }
}
