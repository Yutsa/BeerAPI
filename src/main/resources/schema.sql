DROP TABLE IF EXISTS beers;
CREATE TABLE beers (
  id int(21) NOT NULL auto_increment,
  brewery_id int(21) NULL default '0',
  name varchar(255) NOT NULL default '',
  cat_id int(11) NULL default '0',
  style_id int(11) NULL default '0',
  abv float NULL default '0',
  ibu float NULL default '0',
  srm float NULL default '0',
  upc int(40) NULL default '0',
  filepath varchar(255) NULL default '',
  descript text NULL,
  add_user int(11) NULL default '0',
  last_mod datetime NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
)
