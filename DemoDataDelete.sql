use parkingdb;

#Delete from parking system#
DELETE FROM parkingdb.parking_system WHERE system_id=1;
DELETE FROM parkingdb.parking_system WHERE system_id=2;

#delete rule#
DELETE FROM parkingdb.rule WHERE rule_id = 1;
DELETE FROM parkingdb.rule WHERE rule_id = 2;
DELETE FROM parkingdb.rule WHERE rule_id = 3;

#remove rule assignment#
DELETE FROM parkingdb.parking_system_rule WHERE rule_id = 1;
DELETE FROM parkingdb.parking_system_rule WHERE rule_id = 2;
DELETE FROM parkingdb.parking_system_rule WHERE rule_id = 3;

#remove period assignment to rule#
DELETE FROM parkingdb.period WHERE period_id = 1;
DELETE FROM parkingdb.period WHERE period_id = 2;
DELETE FROM parkingdb.period WHERE period_id = 3;