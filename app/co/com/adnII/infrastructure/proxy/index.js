var proxy = require('redbird')({port: 80});

proxy.register("gaps.local/api/v1/companies", "http://localhost:9000/api/v1/companies");
proxy.register("gaps.local/", "http://localhost:4200");