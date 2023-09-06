use parkingdb;
#insert into admin#

INSERT INTO admin (full_Name, email)
VALUES ('Romeisa aliu', 'romeisaaliu1@gmail.com');
INSERT INTO admin (full_Name, email)
VALUES ('Klea Prifti', 'kleaprifti21@gmail.com');

#insert into parking system#


INSERT INTO parkingdb.parking_system (address, firmware_version, first_install_date, unique_identifier, last_update,
                                      total_money,
                                      working_status, admin_id)
VALUES ('Main Street', '1.0', '2022-05-10', 'PSYSTEM001', 'never', '1500', 'WORKING',
        (SELECT id FROM admin WHERE full_name = 'Klea Prifti'));
INSERT INTO parkingdb.parking_system(address, firmware_version, first_install_date, unique_identifier, last_update,
                                     total_money,
                                     working_status, admin_id)
VALUES ('Downtown', '1.2', '2023-01-01', 'PSYSTEM002', 'never', '3000', 'WORKING',
        (SELECT id FROM admin WHERE full_name = 'Romeisa Aliu'));


#insert into rule #
INSERT INTO parkingdb.rule (cost, details, end_time, name, start_time)
VALUES ('1', 'Normal fee', '20:00', 'R1', '08:00');
INSERT INTO parkingdb.rule (cost, details, end_time, name, start_time)
VALUES ('0.5', 'Discount during night', '08:00', 'R2', '20:00');
INSERT INTO parkingdb.rule (cost, details, end_time, name, start_time)
VALUES ('0', 'Free during night', '08:00', 'R3', '20:00');


#assign rule to parking system#
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
VALUES ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM001'),
        (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
VALUES ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM001'),
        (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
VALUES ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM002'),
        (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
VALUES ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM002'),
        (SELECT rule_id FROM rule WHERE name = 'R1'));

#assign period to rule#

use parkingdb;
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('1', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('2', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('3', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('4', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('5', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('6', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('7', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));


INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('7', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('6', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('5', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('4', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('3', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('2', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('1', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));

INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('7', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('6', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('5', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('4', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('3', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('2', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('1', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));

