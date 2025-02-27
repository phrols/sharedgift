#!/bin/bash
set -e

# this script is run when the docker container is started
# it creates the sharedgift database, user and schema
# It runs only if data directory that is empty (cf https://hub.docker.com/_/postgres)
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER sharedgift WITH PASSWORD 'sharedgift';
	CREATE DATABASE sharedgift;
	GRANT ALL PRIVILEGES ON DATABASE sharedgift TO sharedgift;
EOSQL

psql -v ON_ERROR_STOP=1 --username "sharedgift" --dbname "sharedgift" <<-EOSQL
	CREATE SCHEMA sharedgift;
EOSQL
