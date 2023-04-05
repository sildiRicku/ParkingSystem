use parkingdb;

#insert into parking system#
INSERT INTO parkingdb.parking_system(address, firmware_version, first_install_date, unique_identifier, last_update,
                                     total_money, working_status)
VALUES ('City Center', '1.6', '2023-01-01', 'PSYSTEM001', 'never', '1000', 'WORKING');

INSERT INTO parkingdb.parking_system (address, firmware_version, first_install_date, unique_identifier, last_update,
                                      total_money,
                                      working_status)
VALUES ('Main Street', '1.0', '2022-05-10', 'PSYSTEM002', 'never', '1500', 'WORKING');


#insert into rule #
INSERT INTO parkingdb.rule(cost, details, name)
VALUES ('0', 'Free on Weekend', 'R1');
INSERT INTO parkingdb.rule (cost, details, end_time, name, start_time)
VALUES ('1', 'Payment on Weekday', '20:00', 'R2', '08:00');
INSERT INTO parkingdb.rule (cost, details, end_time, name, start_time)
VALUES ('1', 'Payment Everyday', '20:00', 'R3', '08:00');


#assign rule to parking system#
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
values ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM001'),
        (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
values ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM001'),
        (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.parking_system_rule(parking_system_id, rule_id)
values ((SELECT system_id FROM parking_system WHERE unique_identifier = 'PSYSTEM002'),
        (SELECT rule_id FROM rule WHERE name = 'R3'));

#assign period to rule#
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('6', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('7', '1', (SELECT rule_id FROM rule WHERE name = 'R1'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('1', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('2', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('3', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('4', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('5', '2', (SELECT rule_id FROM rule WHERE name = 'R2'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('1', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('2', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('3', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('4', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('5', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('6', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));
INSERT INTO parkingdb.period (day_number, period_id, rule_applied_id)
VALUES ('7', '3', (SELECT rule_id FROM rule WHERE name = 'R3'));



