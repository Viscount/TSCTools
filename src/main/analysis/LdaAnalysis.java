package main.analysis;

import main.lda.Corpus;
import main.lda.LdaGibbsSampler;
import main.lda.LdaUtil;

import java.util.Map;

/**
 * Created by Jaric Liao on 2015/12/23.
 */
public class LdaAnalysis {

    public static void main(String[] args) throws Exception{
        // 1. Load corpus from disk
        Corpus corpus = Corpus.loadFolder("window");
        // 2. Create a LDA sampler
        LdaGibbsSampler ldaGibbsSampler = new LdaGibbsSampler(corpus.getDocument(), corpus.getVocabularySize());
        // 3. Train it
        ldaGibbsSampler.gibbs(5);
        // 4. The phi matrix is a LDA model, you can use LdaUtil to explain it.
        double[][] phi = ldaGibbsSampler.getPhi();
        Map<String, Double>[] topicMap = LdaUtil.translate(phi, corpus.getVocabulary(), 10);
        LdaUtil.explain(topicMap);
    }
}
