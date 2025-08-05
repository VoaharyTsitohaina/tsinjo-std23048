CREATE TABLE donation (
                          id SERIAL PRIMARY KEY,
                          donor_email VARCHAR(255) NOT NULL,
                          donor_name VARCHAR(255) NOT NULL,
                          payment_id VARCHAR(255) NOT NULL,
                          psp_type VARCHAR(50) NOT NULL,
                          psp_payment_id VARCHAR(255) NOT NULL,
                          amount INT NOT NULL,
                          status VARCHAR(50) NOT NULL,
                          creation_instant TIMESTAMP NOT NULL
);

CREATE TABLE help (
                      id SERIAL PRIMARY KEY,
                      beneficiary_email VARCHAR(255) NOT NULL,
                      beneficiary_name VARCHAR(255) NOT NULL,
                      payment_id VARCHAR(255) NOT NULL,
                      psp_type VARCHAR(50) NOT NULL,
                      psp_payment_id VARCHAR(255) NOT NULL,
                      amount INT NOT NULL,
                      status VARCHAR(50) NOT NULL,
                      accident_description TEXT NOT NULL,
                      creation_instant TIMESTAMP NOT NULL
);
