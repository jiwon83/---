# -- 코드를 입력하세요
(SELECT date_format(sales_date, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
from online_sale as a
where date_format(a.sales_date, '%Y-%m') = '2022-03'
union
SELECT date_format(sales_date, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, NULL as USER_ID, SALES_AMOUNT
from offline_sale as b 
where date_format(b.sales_date, '%Y-%m') = '2022-03' )
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID
;

# SELECT date_format(sales_date, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, 'NULL' as USER_ID, SALES_AMOUNT
# from offline_sale as b
# where date_format(b.sales_date, '%Y-%m') = '2022-03';

# SELECT *
# FROM OFFLINE_SALE;