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
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;

import java.io.Reader;

import static org.neo4j.index.impl.lucene.LuceneDataSource.LUCENE_VERSION;

final class CustomAnalyzer extends Analyzer
{
    static boolean called;

    @Override
    protected TokenStreamComponents createComponents( String fieldName, Reader reader )
    {
        called = true;

        Tokenizer source = new WhitespaceTokenizer( LUCENE_VERSION, reader );
        TokenFilter filter = new LowerCaseFilter( LUCENE_VERSION,  source);

        return new TokenStreamComponents( source, filter );
    }
}
