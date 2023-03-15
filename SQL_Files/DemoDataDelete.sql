use parkingdb;

#Delete from parking system#
DELETE
FROM parkingdb.parking_system;

#delete rule#
DELETE
FROM parkingdb.rule;

#remove rule assignment#
DELETE
FROM parkingdb.parking_system_rule;

#remove period assignment to rule#
DELETE
FROM parkingdb.period;