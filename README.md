## Commands to run tests from cmd
___
***Local run (you must start Appium Server first):***
```bash  
appium server --base-path /wd/hub
```
```bash  
gradle clean test -Denv=local
```

***Remote run in BrowserStack:***
```bash  
gradle 
clean test 
-Denv=remote 
-Dusername=YOUR_USERNAME
-DaccessKey=YOUR_ACCESS_KEY
```
where <code>YOUR_USERNAME</code> & <code>YOUR_ACCESS_KEY</code> your BrowserStack credentials.
___