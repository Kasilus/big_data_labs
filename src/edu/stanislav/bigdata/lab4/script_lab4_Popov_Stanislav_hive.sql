SELECT avg(eurusd_max), avg(eurgbp_max), avg(eurchf_max)
FROM market.forex
WHERE substr(eurusd_date,1,4) = ${input_year};
