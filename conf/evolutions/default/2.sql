# --- Sample dataset

# --- !Ups

insert into category (id,name) values ( 1,'CDs' );
insert into category (id,name) values ( 2,'Hats' );
insert into category (id,name) values ( 3,'Shirts' );
insert into category (id,name) values ( 4,'Hoodies' );
insert into category (id,name) values ( 5,'Posters' );
insert into category (id,name) values ( 6,'Cups/Glasses' );

insert into chart (id,song,artist,release) values ( 1,'Shape of you','Ed Sheeran','6/1/2017' );
insert into chart (id,song,artist,release) values ( 2,'Symphony','Clean Bandit ft Zara Larsson','6/3/2017' );
insert into chart (id,song,artist,release) values ( 3,'Galway Girl','Ed Sheeran','17/3/2017' );
insert into chart (id,song,artist,release) values ( 4,'Sign of the times','Harry Styles','10/4/2017' );
insert into chart (id,song,artist,release) values ( 5,'Passionfruit','Drake','18/3/2017' );
insert into chart (id,song,artist,release) values ( 6,'Humble','Kendrick Lamar','11/4/2017' );
insert into chart (id,song,artist,release) values ( 7,'Something just like this','Chainsmokers & Coldplay','22/2/2017' );
insert into chart (id,song,artist,release) values ( 8,'Solo Dance','Martin Jensen','4/11/2016' );
insert into chart (id,song,artist,release) values ( 9,'Stay','Zedd & Alessia Cara','23/2/2017' );
insert into chart (id,song,artist,release) values ( 10,'Issues','Julia Michaels','13/1/2017' );

insert into product (id,name,description,stock,price) values ( 1,'Ed Sheeran – ÷ (Divide) CD','÷ (Divide) is the third studio CD from acoustic singer songwriter Ed Sheeran',100,13.00 );
insert into product (id,name,description,stock,price) values ( 2,'Coldplay – A Head Full Of Dreams (CD)','CD version of Coldplay – A Head Full Of Dreams',45,14.00 );
insert into product (id,name,description,stock,price) values ( 3,'Ed Sheeran The A Team Embroidered Cotton Twill Hat','Ed sheeran the a team Embroidered Cotton Twill Hat is professionally designed and embroidered in U.S.',50,25.00 );
insert into product (id,name,description,stock,price) values ( 4,'Drake Snapback','Drake Snapback, Rapper, Rap Artist embroidered Music Design Hat',45,11.00 );

insert into category_product (category_id,product_id) values (1,1);
insert into category_product (category_id,product_id) values (1,2);
insert into category_product (category_id,product_id) values (2,3);
insert into category_product (category_id,product_id) values (2,4);

insert into user (email,name,address,password) values ( 'admin@products.com', 'Alice Admin', 'Admin Road', 'admin' );
insert into user (email,name,address,password) values ( 'manager@products.com', 'Bob Manager', 'manger Street', 'manager' );
insert into user (email,name,address,password) values ( 'customer@products.com', 'Charlie Customer', 'Customer Green', 'customer' );