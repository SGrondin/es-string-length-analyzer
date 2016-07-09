package org.elasticsearch.plugin.analysis.stringlength;

import java.util.Collection;
import java.util.Collections;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.StringLengthAnalysisBinderProcessor;
import org.elasticsearch.indices.analysis.IndicesAnalysisModule;
import org.elasticsearch.plugins.Plugin;

public class AnalysisStringLengthPlugin extends Plugin {

    @Override
    public String name() {
        return "es-string-length-analyzer";
    }

    @Override
    public String description() {
        return "An efficient Analyzer and TokenFilter that indexes strings by their length";
    }

    @Override
    public Collection<Module> nodeModules() {
        return Collections.<Module>singletonList(new IndicesAnalysisModule());
    }

    public void onModule(AnalysisModule module) {
        module.addProcessor(new StringLengthAnalysisBinderProcessor());
    }
}
