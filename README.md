#es-string-length-analyzer

Efficient low level Analyzer and TokenFilter for ElasticSearch 2.2.1 and 2.4.1. No other versions are supported at the moment, please open an Issue (or submit a PR) if you'd like to see your version being supported.

#### As an Analyzer:

```json
{
  "url": {
    "type": "string",
    "index": "analyzed",
    "analyzer": "stringlength"
  }
}
```

Example field value: `https://en.wikipedia.org/wiki/NASA#Project_Apollo_.281961.E2.80.9372.29`

Indexed field data: `["71"]`

This allows *extremely* fast filters on strings. By using a [Multi-Field](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/_multi_fields.html) it becomes possible to fully analyze and tokenize a string with the most appropriate analyzer for your needs. Most analyzers can do `match` `phrase_prefix` in an efficient manner. By doing a filter on the same field, the search space is reduced tremendously, which makes it much faster.

The combination of an `analyzed` field (not using the `keyword` analyzer) being queried with a `match` `phrase_prefix` **and** filtered by `stringlength` at the same time allows efficient exact matches that would normally not be feasible without sequentially scanning the entire inverted index.

It's also an excellent low impact complement to `not_analyzed` for fields that have an extreme variance. Even tens of millions of unique string values will not bloat up the inverted index, because there are bound to be many (unique) strings with the same length. If for some reason an expensive sequential scan search such as a `regexp`, `script` or `prefix` search is needed, it's then possible to instantly reduce the search space with a filter, obviously using the `stringlength` analyzer to force usage of the `stringlength` part of the multi-field.

#### As a TokenFilter

There's an [entire chapter](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/analysis-tokenfilters.html) on TokenFilters and how to use them in custom Analyzers.

```json
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_analyzer": {
          "type": "custom",
          "tokenizer": "whitespace",
          "filter": [
            "stringlength"
          ]
        }
      }
    }
  }
}
```

Example field value: `You Know, for Search`

Indexed field data: `["3", "5", "3", "6"]`

### Install

From your ES home directory:

#### ES 2.2.1
`bin/plugin install https://github.com/SGrondin/es-string-length-analyzer/releases/download/2.2.1/es-string-length-analyzer-2.2-plugin.zip`

#### ES 2.4.1
`bin/plugin install https://github.com/SGrondin/es-string-length-analyzer/releases/download/2.4.1/es-string-length-analyzer-2.4-plugin.zip`
