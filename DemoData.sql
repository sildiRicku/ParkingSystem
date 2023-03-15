use parkingdb;
#insert into parking system#
INSERT INTO parkingdb.parking_system ( address, firmware_version, first_install_date, last_update, total_money, working_status) VALUES ( 'City Center', '1.6', '2023-01-01', 'never', '1000', 'WORKING');
INSERT INTO parkingdb.parking_system ( address, firmware_version, first_install_date, last_update, total_money, working_status) VALUES ( 'Main Street', '1.0', '2022-05-10', 'never', '1500', 'WORKING');
#Delete from parking system#
DELETE FROM parkingdb.parking_system WHERE system_id=1;
DELETE FROM parkingdb.parking_system WHERE system_id=2;

#insert into rule #
INSERT INTO parkingdb.rule( cost, details, name) VALUES ( '0', 'Free on Weekend', 'R1');
INSERT INTO parkingdb.rule ( cost, details,end_time, name, start_time) VALUES ( '1', '1 euro/hour weekday', '20:00','R2','08:00');
INSERT INTO parkingdb.rule ( cost, details,end_time, name, start_time) VALUES ( '1', '1 euro/hour everyday', '20:00','R3','08:00' );
#delete rule#
DELETE FROM parkingdb.rule WHERE rule_id=1;
DELETE FROM parkingdb.rule WHERE rule_id=2;
DELETE FROM parkingdb.rule WHERE rule_id=3;

#assign rule to parking system#
INSERT INTO parkingdb.parking_system_rule(parking_system_id,rule_id) values('1','1') ;
INSERT INTO parkingdb.parking_system_rule(parking_system_id,rule_id) values('2','2') ;
INSERT INTO parkingdb.parking_system_rule(parking_system_id,rule_id) values('2','3') ;
#remove rule assignment#
DELETE FROM parkingdb.parking_system_rule WHERE rule_id=1;
DELETE FROM parkingdb.parking_system_rule WHERE rule_id=2;
DELETE FROM parkingdb.parking_system_rule WHERE rule_id=3;

#assign period to rule#
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('6', '1','1');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('7', '1','1');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('1', '2','2');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('2', '2','2');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('3', '2','2');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('4', '2','2');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('5', '2','2');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('1', '3','3');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('2', '3','3');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('3', '3','3');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('4', '3','3');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('5', '3','3');
INSERT INTO parkingdb.period (day_number, period_id,rule_applied_id) VALUES ('6', '3','3');
INSERT INTO parkingdb.period (day_number,period_id,rule_applied_id) VALUES  ('7', '3','3');
#remove period assignment to rule#
DELETE FROM parkingdb.period WHERE period_id=1;
DELETE FROM parkingdb.period WHERE period_id=2;
DELETE FROM parkingdb.period WHERE period_id=3;

