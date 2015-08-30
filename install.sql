CREATE USER 'elasticpotdev'@'localhost' IDENTIFIED BY 'elasticpotpw';
CREATE USER 'elasticpotprod'@'localhost' IDENTIFIED BY 'elasticpotpw';

CREATE DATABASE elasticpotdev;
CREATE DATABASE elasticpotprod;

GRANT ALL PRIVILEGES ON elasticpotdev.* TO 'elasticpotdev'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON elasticpotprod.* TO 'elasticpotprod'@'localhost' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON elasticpotdev.* TO 'elasticpotdev'@'localhost' IDENTIFIED BY 'elasticpotpw';
GRANT ALL PRIVILEGES ON elasticpotprod.* TO 'elasticpotprod'@'localhost' IDENTIFIED BY 'elasticpotppw';

FLUSH PRIVILEGES;
