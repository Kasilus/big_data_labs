create table market_forex(eurusd_date date, eurusd_max double, eurgbp_date date, eurgbp_max double, eurchf_date date, eurchf_max int);

insert into market_forex VALUES('2014-09-30', 1.0906, '2014-09-30', 0.8865, '2014-09-30', 1.0877);
insert into market_forex VALUES('2014-09-30', 1.0904, '2014-09-30', 0.8865, '2014-09-30', 1.0877);
insert into market_forex VALUES('2014-09-30', 1.0902, '2014-09-30', 0.8866, '2014-09-30', 1.0877);

select * from market_forex;


select substr((select eurusd_date from market_forex where eurusd_max =  1.0906),1,4);


SELECT avg(eurusd_max), avg(eurgbp_max), avg(eurchf_max)
FROM market_forex
GROUP BY substr(eurusd_date,1,4) = ?;
