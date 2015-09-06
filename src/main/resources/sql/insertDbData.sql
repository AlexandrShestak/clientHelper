INSERT INTO clientHelper.topic (name) VALUE ("problems with balance");
INSERT INTO clientHelper.topic (name) VALUE ("change tariff plan");
INSERT INTO clientHelper.topic (name) VALUE ("install a temporary lock");
INSERT INTO clientHelper.topic (name) VALUE ("add a service");
INSERT INTO clientHelper.topic (name) VALUE ("remove service");
INSERT INTO clientHelper.topic (name) VALUE ("forgotten PIN");

INSERT INTO clientHelper.inquiry (description, creation_date, customer_name, topic_id) VALUES ("withdraw too much money after the bell","2015-10-12","IvanovIvan","1");
INSERT INTO clientHelper.inquiry (description, creation_date, customer_name, topic_id) VALUES ("change tariff plan from red to extra","2015-9-12","MigelXoce","2");
INSERT INTO clientHelper.inquiry (description, creation_date, customer_name, topic_id) VALUES ("install a temporary lock for 3 weeks","2015-1-12","MigelXoce","3");
INSERT INTO clientHelper.inquiry (description, creation_date, customer_name, topic_id) VALUES ("add unlimited calls","2015-6-11","BarakNigrian","4");
INSERT INTO clientHelper.inquiry (description, creation_date, customer_name, topic_id) VALUES ("remove unlimited calls","2015-9-11","GeorgeGroo","5");
INSERT INTO clientHelper.inquiry (description, creation_date, customer_name, topic_id) VALUES ("forgotten PIN on number +375331111111","2015-3-8","ShevKaterina","6");

INSERT INTO clientHelper.attributeOfInquiry (name, value, inquiry_id) VALUES ("amount","100$","1");
INSERT INTO clientHelper.attributeOfInquiry (name, value, inquiry_id) VALUES ("tarif plan","superPuper","1");