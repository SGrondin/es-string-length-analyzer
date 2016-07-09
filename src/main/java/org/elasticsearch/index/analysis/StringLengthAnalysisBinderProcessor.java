package org.elasticsearch.index.analysis;

public class StringLengthAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("stringlength", StringLengthAnalyzerProvider.class);
    }

    @Override
    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        tokenFiltersBindings.processTokenFilter("stringlength", StringLengthTokenFilterFactory.class);
    }
}
