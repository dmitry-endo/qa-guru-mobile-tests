## Команды для запуска из терминала
___
***Локальный запуск (для запуска необходим запущенный Appium Server):***
```bash  
gradle clean test -Denv=local
```

***Удалённый запуск в BrowserStack:***
```bash  
gradle 
clean test 
-Denv=remote 
-Dusername=${YOUR_USERNAME}
-DaccessKey=${YOUR_ACCESS_KEY}
```
___