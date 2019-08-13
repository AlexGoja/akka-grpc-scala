#!/bin/sh

# Helper script to export values for aws rds mysql connection

echo "Enter host db url"
read -r URL_DB_INPUT

echo "Enter user for db"
read -r USER_DB_INPUT

echo "Enter the db password"
read -sr PASSWORD_DB_INPUT

export PASSWORD_DB=$PASSWORD_DB_INPUT
export URL_DB=$URL_DB_INPUT
export USER_DB=$USER_DB_INPUT