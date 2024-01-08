-- liquibase formatted sql

-- changeset antonov333:5
CREATE table ads(

    id BIGSERIAL PRIMARY KEY,

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

-- changeset antonov333:50
ALTER table ads DROP id;
ALTER table ads ADD column id SERIAL;

-- changeset antonov333:51
ALTER table ads DROP id;
ALTER table ads ADD column id BIGSERIAL;

