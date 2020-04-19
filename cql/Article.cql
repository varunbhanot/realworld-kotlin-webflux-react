CREATE KEYSPACE conduit WITH REPLICATION =  {'class':'SimpleStrategy', 'replication_factor':1};


CREATE KEYSPACE article WITH REPLICATION =  {'class':'SimpleStrategy', 'replication_factor':1};

CREATE TABLE articles (
    articleId uuid,
    slug text,
    title text,
    description text,
    body text,
    tagList LIST<text>,
    createdAt timestamp,
    updatedAt timestamp,
    favorited boolean,
    favoritesCount int,
    authorName text,
    PRIMARY KEY (articleId,updatedAt));


CREATE MATERIALIZED VIEW articles_by_slug AS
    SELECT articleId,slug ,
    title ,
    description ,
    body ,
    tagList ,
    createdAt ,
    updatedAt ,
    favorited ,
    favoritesCount ,
    authorName  FROM articles
        WHERE slug IS NOT NULL and updatedAt IS NOT NULL AND articleId is not null
    PRIMARY KEY ((slug),articleId,updatedAt)
    WITH CLUSTERING ORDER BY (updatedAt DESC)

