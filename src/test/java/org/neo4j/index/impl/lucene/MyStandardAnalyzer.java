/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.index.impl.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public final class MyStandardAnalyzer extends Analyzer
{
    private final StandardAnalyzer actual;

    public MyStandardAnalyzer()
    {
        CharArraySet stopwords = new CharArraySet( LuceneDataSource.LUCENE_VERSION,
                Arrays.asList( "just", "some", "words" ), true );
        actual = new StandardAnalyzer( LuceneDataSource.LUCENE_VERSION, stopwords);
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader)
    {
        // copied from StandardAnalyzer#createComponents (we cannot call it directly, because it is protected)
        final StandardTokenizer src = new StandardTokenizer(LuceneDataSource.LUCENE_VERSION, reader);
        src.setMaxTokenLength(actual.getMaxTokenLength());
        TokenStream tok = new StandardFilter(LuceneDataSource.LUCENE_VERSION, src);
        tok = new LowerCaseFilter(LuceneDataSource.LUCENE_VERSION, tok);
        tok = new StopFilter(LuceneDataSource.LUCENE_VERSION, tok, this.actual.getStopwordSet());
        return new TokenStreamComponents(src, tok) {
            @Override
            protected void setReader(final Reader reader) throws IOException {
                src.setMaxTokenLength(actual.getMaxTokenLength());
                super.setReader(reader);
            }
        };
    }
}
