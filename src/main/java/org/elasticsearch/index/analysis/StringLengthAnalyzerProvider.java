package org.elasticsearch.index.analysis;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettingsService;

/**
 */
public class StringLengthAnalyzerProvider extends AbstractIndexAnalyzerProvider<StringLengthAnalyzer> {

    private final StringLengthAnalyzer analyzer;

    @Inject
    public StringLengthAnalyzerProvider(Index index, IndexSettingsService indexSettingsService, Environment env, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettingsService.getSettings(), name, settings);
        analyzer = new StringLengthAnalyzer();
    }

    @Override
    public StringLengthAnalyzer get() {
        return this.analyzer;
    }
}
