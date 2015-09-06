CREATE TABLE clientHelper.topic
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);
ALTER TABLE clientHelper.topic ADD CONSTRAINT unique_id UNIQUE (id);

CREATE TABLE clientHelper.inquiry
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    description VARCHAR(3000) NOT NULL,
    creation_date DATE NOT NULL,
    customer_name VARCHAR(30) NOT NULL,
    topic_id INT NOT NULL
);
ALTER TABLE clientHelper.inquiry ADD CONSTRAINT unique_id UNIQUE (id);

CREATE TABLE clientHelper.attributeOfInquiry
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    value VARCHAR(200) NOT NULL,
    inquiry_id INT NOT NULL
);
ALTER TABLE clientHelper.attributeOfInquiry ADD CONSTRAINT unique_id UNIQUE (id);