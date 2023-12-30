-- liquibase formatted sql

-- changeset antonov333:5
CREATE table ads(

    pk BIGSERIAL PRIMARY KEY,

--    @Column(name = "Author")
--    private Integer author;
    author int,

--    @Column(name = "URL")
--    private String image;
    URL TEXT,

--    @Column(name = "Price")
--    private Integer price;
    Price int,

--    @Column(name = "Title")
--    private String title;
    Title TEXT

    )

