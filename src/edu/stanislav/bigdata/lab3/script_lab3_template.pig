--середнє значення для максимум ціни для 3-х пар за певний рік
forex_history = LOAD 'hdfs://hadoop-master:54310/user/hduser/market/EURUSD_GBP_CHF.csv' USING
   PigStorage(',') as (eurusd_date:chararray, eurusd_time:chararray, eurusd_open:float, eurusd_max:float, eurusd_min:float, eurusd_close:float, eurusd_volume:float,
                       eurgbp_date:chararray, eurgbp_time:chararray, eurgbp_open:float, eurgbp_max:float, eurgbp_min:float, eurgbp_close:float, eurgbp_volume:float,
					   eurchf_date:chararray, eurchf_time:chararray, eurchf_open:float, eurchf_max:float, eurchf_min:float, eurchf_close:float, eurchf_volume:float);

eurusd_data = FOREACH forex_history GENERATE eurusd_date, eurusd_time, eurusd_open, eurusd_max, eurusd_min, eurusd_close, eurusd_volume;
eurgbp_data = FOREACH forex_history GENERATE eurgbp_date, eurgbp_time, eurgbp_open, eurgbp_max, eurgbp_min, eurgbp_close, eurgbp_volume;
eurchf_data = FOREACH forex_history GENERATE eurchf_date, eurchf_time, eurchf_open, eurchf_max, eurchf_min, eurchf_close, eurchf_volume;

eurusd_group = GROUP eurusd_data All;
eurgbp_group = GROUP eurgbp_data All;
eurchf_group = GROUP eurchf_data All;

eurusd_volume_avg = FOREACH eurusd_group GENERATE AVG(eurusd_data.eurusd_volume);
eurgbp_volume_avg = FOREACH eurgbp_group GENERATE AVG(eurgbp_data.eurgbp_volume);
eurchf_volume_avg = FOREACH eurchf_group GENERATE AVG(eurchf_data.eurchf_volume);

result = UNION eurusd_volume_avg, eurgbp_volume_avg, eurchf_volume_avg;

STORE result INTO 'hdfs://hadoop-master:54310/user/hduser/results/Popov_Stanislav_lab3' USING PigStorage (',');


