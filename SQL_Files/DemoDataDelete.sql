use
parkingdb;
#delete transactions#
DELETE
FROM parkingdb.transactions;
#remove period assignment to rule #
DELETE
FROM parkingdb.period;
#remove rule assignment#
DELETE
FROM parkingdb.parking_system_rule;
#delete rule#
DELETE
FROM parkingdb.rule;
#Delete from parking system#
DELETE
FROM parkingdb.parking_system;
#delete admin#
DELETE
FROM parkingdb.admin;
#delete loginCredentials#
DELETE
FROM parkingdb.user_login_info;
DELETE
FROM parkingdb.authority






