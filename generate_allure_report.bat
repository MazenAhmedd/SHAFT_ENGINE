@echo off
set path=C:\Users\MMohamed\.m2\repository\allure\allure-2.13.9\bin;C:\Program Files\Java\jdk-15.0.2\bin;%path%
allure serve allure-results
pause
exit