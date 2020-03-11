call runcrud.bat
if "%ERRORLEVEL%" == "0" goto site
echo.
echo GRADLEW BUILD has errors - breaking showtasks
goto fail

:site
cd "C:\Program Files (x86)\Google\Chrome\Application\"
start chrome.exe
start http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.