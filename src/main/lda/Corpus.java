package main.lda;

import main.entity.Vector;
import main.util.JsonUtil;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jaric Liao on 2015/12/23.
 */
public class Corpus {
    List<int[]> documentList;
    Vocabulary vocabulary;

    public Corpus()
    {
        documentList = new LinkedList<int[]>();
        vocabulary = new Vocabulary();
    }

    public int[] addDocument(List<String> document)
    {
        int[] doc = new int[document.size()];
        int i = 0;
        for (String word : document)
        {
            doc[i++] = vocabulary.getId(word, true);
        }
        documentList.add(doc);
        return doc;
    }

    public int[][] toArray()
    {
        return documentList.toArray(new int[0][]);
    }

    public int getVocabularySize()
    {
        return vocabulary.size();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        for (int[] doc : documentList)
        {
            sb.append(Arrays.toString(doc)).append("\n");
        }
        sb.append(vocabulary);
        return sb.toString();
    }

    /**
     * Load documents from disk
     *
     * @param folderPath is a folder, which contains text documents.
     * @return a corpus
     * @throws IOException
     */
    public static Corpus loadFolder(String folderPath) throws IOException
    {
        Corpus corpus = new Corpus();
        File folder = new File(folderPath);
        for (File file : folder.listFiles())
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String content = "";
            String line;
            List<String> wordList = new LinkedList<String>();
            while ((line = br.readLine()) != null) content += line;
            br.close();
            Vector vector = JsonUtil.toObject(content, Vector.class);
            for (Map.Entry<String,Object> entry : vector.getDetail().entrySet() ){
                String word = entry.getKey();
                long count = (long)entry.getValue();
                for ( long i=0; i<count; i++ ) wordList.add(word);
            }
            corpus.addDocument(wordList);
        }
        if (corpus.getVocabularySize() == 0) return null;

        return corpus;
    }

    /**
     * Load documents from disk
     *
     * @param folderPath is a folder, which contains text documents.
     * @return a corpus
     * @throws IOException
     */
    public static Corpus load(String folderPath) throws IOException
    {
        Corpus corpus = new Corpus();
        File folder = new File(folderPath);
        for (File file : folder.listFiles())
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            List<String> wordList = new LinkedList<String>();
            while ((line = br.readLine()) != null)
            {
                String[] words = line.split(" ");
                for (String word : words)
                {
                    if (word.trim().length() < 2) continue;
                    wordList.add(word);
                }
            }
            br.close();
            corpus.addDocument(wordList);
        }
        if (corpus.getVocabularySize() == 0) return null;

        return corpus;
    }

    public Vocabulary getVocabulary()
    {
        return vocabulary;
    }

    public int[][] getDocument()
    {
        return toArray();
    }

    public static int[] loadDocument(String path, Vocabulary vocabulary) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        List<Integer> wordList = new LinkedList<Integer>();
        while ((line = br.readLine()) != null)
        {
            String[] words = line.split(" ");
            for (String word : words)
            {
                if (word.trim().length() < 2) continue;
                Integer id = vocabulary.getId(word);
                if (id != null)
                    wordList.add(id);
            }
        }
        br.close();
        int[] result = new int[wordList.size()];
        int i = 0;
        for (Integer integer : wordList)
        {
            result[i++] = integer;
        }
        return result;
    }
}